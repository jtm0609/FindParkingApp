package com.jtmcompany.parkingapplication.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    // LiveData를 사용하여 ProgessBar를 On/Off 시킨다.
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun showProgress() {
        Log.d("tak","showProgress!")
        _isLoading.value = true
    }

    fun hideProgress() {
        Log.d("tak","hideProgress!")
        _isLoading.value = false
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}