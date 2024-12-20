package com.jtmcompany.parkingapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.usecase.GetLocalParkInfoUseCase
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.ResourceProvider
import com.jtmcompany.parkingapplication.utils.SingleLiveEvent
import com.jtmcompany.parkingapplication.utils.applyDistance
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.roundToInt

@HiltViewModel
class ParkSearchViewModel @Inject constructor(
    private val getLocalParkInfoUsecase: GetLocalParkInfoUseCase,
    private val resourceProvider: ResourceProvider

) : BaseViewModel() {

    //주차장 정보가 저장되는 변수
    private val _parkLocalList = MutableLiveData<ArrayList<ParkInfo>>()
    val parkLocalList: LiveData<ArrayList<ParkInfo>> get() = _parkLocalList

    private val _clickedParkInfo = SingleLiveEvent<ParkInfo>()
    val clickedParkInfo: LiveData<ParkInfo> = _clickedParkInfo

    private val _clickedParkSearch = SingleLiveEvent<Unit>()
    val clickedParkSearch: LiveData<Unit> = _clickedParkSearch

    // toast 메시지
    var keyword = MutableLiveData<String>()

    private fun requestLocalPark() {
        compositeDisposable.add(
            getLocalParkInfoUsecase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnNext { hideProgress() }
                .subscribe({ parks ->
                    if (parks.isNotEmpty()) {
                        _parkLocalList.value = parks as ArrayList<ParkInfo>
                    }
                }) {
                    Log.d("tak", "requestLocalPark throwable: " + it.message)
                })
    }

    private fun checkMatchKeyword(parkInfo: ParkInfo, keyword: String): Boolean {
        return parkInfo.run {
            prkplceNm.contains(keyword, ignoreCase = true)
                    || rdnmadr.contains(keyword, ignoreCase = true)
                    || lnmadr.contains(keyword, ignoreCase = true)
        }
    }

    fun filterParkList(
        parkInfoList: List<ParkInfo>,
        keyword: String,
        sectionOption: String,
        typeOption: String,
        chargeOption: String,
        userLatitude: Double,
        userLongitude: Double
    ): List<ParkInfo> {
        return parkInfoList.filter { parkInfo ->
            val matchesKeyword = checkMatchKeyword(parkInfo, keyword)
            val matchesSection = sectionOption == "주차장구분" || parkInfo.prkplceSe == sectionOption
            val matchesType = typeOption == "주차장유형" || parkInfo.prkplceType == typeOption
            val matchesCharge = chargeOption == "요금정보" || parkInfo.parkingchrgeInfo == chargeOption
            matchesKeyword && matchesSection && matchesType && matchesCharge
        }.map { parkInfo ->
            parkInfo.applyDistance(
                userLatitude,
                userLongitude,
                parkInfo.latitude.toDouble(),
                parkInfo.longitude.toDouble()
            )
        }
    }

    //계산한 거리 단위(km, m)로 변환
    fun getDistanceStr(distance: Double): String {
        val distanceStr: String = if (distance > 1000) { //1km 이상
            (distance / 1000).roundToInt().toString() + "km"
        } else { //소수점 버림
            floor(distance).toString() + "m"
        }
        return distanceStr
    }

    fun onClickParkInfo(parkInfo: ParkInfo) {
        _clickedParkInfo.value = parkInfo
    }

    fun onClickParkSearch() {
        val keywordText = keyword.value ?: ""
        if (keywordText.isEmpty()) {
            showToast(resourceProvider.getString(R.string.msg_check_empty_park_))
            return
        }

        if (keywordText.length < 2) {
            showToast(resourceProvider.getString(R.string.msg_check_word_count))
            return
        }
        _clickedParkSearch.call()

        requestLocalPark()
    }
}