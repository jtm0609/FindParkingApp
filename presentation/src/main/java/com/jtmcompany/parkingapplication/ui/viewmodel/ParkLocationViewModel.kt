package com.jtmcompany.parkingapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.usecase.GetParkInfoUseCase
import com.jtmcompany.domain.usecase.InsertLocalParkUseCase
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ParkLocationViewModel @Inject constructor(
    private val insertLocalParkUseCase: InsertLocalParkUseCase,
    private val getParkInfoUseCase: GetParkInfoUseCase,
    private val networkManager: NetworkManager,
) : BaseViewModel() {

    //주차장 정보가 저장되는 변수
    private val _parkList = MutableLiveData<ArrayList<ParkInfo>>()
    val parkList: LiveData<ArrayList<ParkInfo>> get() = _parkList

    private val _totalCnt = MutableLiveData<Int>()
    val totalCnt: LiveData<Int> get() = _totalCnt

    fun requestParkInfo(numOfRows: Int) {
        if (!networkManager.checkNetworkState()) {
            return
        };
        compositeDisposable.add(
            getParkInfoUseCase.execute(numOfRows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnNext { hideProgress() }
                .subscribe({ list ->
                    if (list.isNotEmpty()) {
                        if (numOfRows == 1) {
                            _totalCnt.value = list[0].totalCnt.toInt()
                        } else {
                            _parkList.value = list as ArrayList<ParkInfo>
                        }

                    }
                }, {
                    Log.d("tak", "requestParkInfo throwable: " + it.message)
                })
        )
    }
    fun insertLocalPark(parks: List<ParkInfo>) {
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
}