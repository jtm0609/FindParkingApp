package com.jtmcompany.parkingapplication.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo
import com.jtmcompany.domain.usecase.GetParkInfoUsecase
import com.jtmcompany.domain.usecase.GetParkOperInfoUsecase
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
    private val networkManager: NetworkManager
)  : BaseViewModel() {

    //주차장 정보가 저장되는 변수
    private val _parkList = MutableLiveData<ArrayList<ParkInfo>>()
    val parkList: LiveData<ArrayList<ParkInfo>> get() = _parkList

    //주차장 운영 정보가 저장되는 변수
    private val _parkOperList = MutableLiveData<ArrayList<ParkOperInfo>>()
    val parkOperList: LiveData<ArrayList<ParkOperInfo>> get() = _parkOperList

    // toast 메시지
    private val _toastMsg = MutableLiveData<MessageSet>()
    val toastMsg: LiveData<MessageSet> get() = _toastMsg

    fun requestParkInfo() {
        if (!networkManager.checkNetworkState()){
            _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
            return
        };
        compositeDisposable.add(
            getParkInfoUsecase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ parks ->
                    if (parks.isEmpty()) {
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        _parkList.value = parks as ArrayList<ParkInfo>
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                }, {
                    Log.d("tak","test: "+it.message)
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestParkOperInfo() {
        if (!networkManager.checkNetworkState()){
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
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    enum class MessageSet{
        NETWORK_NOT_CONNECTED,
        ERROR,
        SUCCESS,
        NO_RESULT
    }


}