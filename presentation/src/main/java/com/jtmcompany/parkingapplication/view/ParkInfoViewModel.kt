package com.jtmcompany.parkingapplication.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo
import com.jtmcompany.domain.usecase.GetLocalParkInfoUsecase
import com.jtmcompany.domain.usecase.GetParkInfoUsecase
import com.jtmcompany.domain.usecase.GetParkOperInfoUsecase
import com.jtmcompany.domain.usecase.InsertLocalParkUsecase
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class ParkInfoViewModel @Inject constructor(
    private val getParkInfoUsecase: GetParkInfoUsecase,
    private val getParkOperInfoUsecase: GetParkOperInfoUsecase,
    private val getLocalParkInfoUsecase: GetLocalParkInfoUsecase,
    private val insertLocalParkUsecase: InsertLocalParkUsecase,
    private val networkManager: NetworkManager
) : BaseViewModel() {

    //주차장 정보가 저장되는 변수
    private val _parkList = MutableLiveData<ArrayList<ParkInfo>>()
    val parkList: LiveData<ArrayList<ParkInfo>> get() = _parkList

    //주차장 운영 정보가 저장되는 변수
    private val _parkOperList = MutableLiveData<ArrayList<ParkOperInfo>>()
    val parkOperList: LiveData<ArrayList<ParkOperInfo>> get() = _parkOperList

    //주차장 정보가 저장되는 변수
    private val _parkLocalList = MutableLiveData<ArrayList<ParkInfo>>()
    val parkLocalList: LiveData<ArrayList<ParkInfo>> get() = _parkLocalList

    private val _totalCnt = MutableLiveData<Int>()
    val totalCntCheck: LiveData<Int> get() = _totalCnt

    // toast 메시지
    private val _toastMsg = MutableLiveData<MessageSet>()
    val toastMsg: LiveData<MessageSet> get() = _toastMsg

    fun requestParkInfo(numOfRows: Int) {
        if (!networkManager.checkNetworkState()) {
            _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
            return
        };
        compositeDisposable.add(
            getParkInfoUsecase.execute(numOfRows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ parks ->
                    if (parks.isEmpty()) {
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        if(numOfRows==1){
                            _totalCnt.value=parks.get(0).totalCnt.toInt()
                            _toastMsg.value = MessageSet.REMOTE_CHECK_SUCCESS
                        }else {
                            _parkList.value = parks as ArrayList<ParkInfo>
                            _toastMsg.value = MessageSet.REMOTE_SUCCESS
                        }

                    }
                }, {
                    Log.d("tak", "test: " + it.message)
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestParkOperInfo() {
        if (!networkManager.checkNetworkState()) {
            _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
            return
        };
        compositeDisposable.add(
            getParkOperInfoUsecase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ parkOpers ->
                    if (parkOpers.isEmpty()) {
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        _parkOperList.value = parkOpers as ArrayList<ParkOperInfo>
                        _toastMsg.value = MessageSet.REMOTE_SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestLocalPark() {
        compositeDisposable.add(
            getLocalParkInfoUsecase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ parks ->
                    if (parks.isEmpty()) {
                        _toastMsg.value = MessageSet.ERROR
                    } else {
                        _parkLocalList.value = parks as ArrayList<ParkInfo>
                        _toastMsg.value = MessageSet.LOCAL_SUCCESS
                    }
                }) {
                    Log.d("tak", "test: " + it.message)
                    _toastMsg.value = MessageSet.ERROR
                })
    }

    fun insertLocalPark(parks: List<ParkInfo>) {
        compositeDisposable.add(
            insertLocalParkUsecase.execute(parks)
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

    enum class MessageSet {
        NETWORK_NOT_CONNECTED,
        ERROR,
        REMOTE_CHECK_SUCCESS,
        REMOTE_SUCCESS,
        LOCAL_SUCCESS,
        NO_RESULT
    }


}