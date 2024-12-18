package com.jtmcompany.parkingapplication.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jtmcompany.parkingapplication.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    // LiveData를 사용하여 ProgessBar를 On/Off 시킨다.
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _showToastEvent = SingleLiveEvent<String>()
    val showToastEvent: LiveData<String> = _showToastEvent

    protected fun showProgress() {
        _isLoading.value = true
    }

    protected fun hideProgress() {
        _isLoading.value = false
    }

    protected fun showToast(msg: String) {
        _showToastEvent.postValue(msg)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}