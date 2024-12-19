package com.jtmcompany.parkingapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.usecase.GetParkInfoUseCase
import com.jtmcompany.domain.usecase.GetParkTotalCntUseCase
import com.jtmcompany.domain.usecase.InsertLocalParkUseCase
import com.jtmcompany.domain.usecase.UpdateParkTotalCntUseCase
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.NetworkManager
import com.jtmcompany.parkingapplication.utils.SingleLiveEvent
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ParkLocationViewModel @Inject constructor(
    private val insertLocalParkUseCase: InsertLocalParkUseCase,
    private val getParkInfoUseCase: GetParkInfoUseCase,
    private val updateParkTotalCntUseCase: UpdateParkTotalCntUseCase,
    private val getParkTotalCntUseCase: GetParkTotalCntUseCase,
    private val networkManager: NetworkManager,
) : BaseViewModel() {

    private val _clickedSearch = SingleLiveEvent<ParkInfo>()
    val clickedSearch: LiveData<ParkInfo> = _clickedSearch

    //현재 사용자의 위치(위도)
    private var _userLatitude: Double = 0.0;
    val userLatitude: Double get() = _userLatitude

    //현재 사용자의 위치(위도)
    private var _userLongitude: Double = 0.0;
    val userLongitude: Double get() = _userLongitude

    private var isNeedUpdateDB: Boolean = false

    private fun insertLocalPark(parks: List<ParkInfo>) {
        compositeDisposable.add(
            insertLocalParkUseCase.execute(parks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("tak", "Inserted Successfully")
                    }, { error ->
                        Log.d("tak", "Error Inserting: ${error.message}")
                    }
                )
        )
    }

    fun requestParkInfo(numOfRows: Int) {
        if (!networkManager.checkNetworkState()) {
            return
        }
        compositeDisposable.add(
            getParkInfoUseCase.execute(numOfRows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnNext { hideProgress() }
                .subscribe({ list ->
                    if (list.isEmpty()) return@subscribe
                    if (numOfRows == 1) {
                        val apiTotalCnt = list[0].totalCnt.toInt()
                        val localTotalCnt = getParkTotalCntUseCase.execute()

                        //주차장 totalCnt(서버 정보)가 변경 되었을 때 or 앱 최초 진입시 전체 주차장 정보 서버로부터 불러오기
                        if (apiTotalCnt != localTotalCnt) {
                            updateParkTotalCntUseCase.execute(apiTotalCnt)
                            isNeedUpdateDB = true
                            requestParkInfo(apiTotalCnt)
                        }
                    } else {
                        if (isNeedUpdateDB) {
                            insertLocalPark(list)
                            isNeedUpdateDB = false
                        }
                    }

                }, {
                    Log.d("tak", "requestParkInfo throwable: " + it.message)
                })
        )
    }

    fun onClickSearch() {
        _clickedSearch.call()
    }

    fun setUserLocation(latitude: Double, longitude: Double) {
        _userLatitude = latitude
        _userLongitude = longitude
    }
}