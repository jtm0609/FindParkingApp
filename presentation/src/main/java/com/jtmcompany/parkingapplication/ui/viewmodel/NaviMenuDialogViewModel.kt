package com.jtmcompany.parkingapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.SingleLiveEvent

class NaviMenuDialogViewModel : BaseViewModel() {
    private var _clickedKakaoNavi = SingleLiveEvent<Unit>()
    val clickedKakaoNavi : LiveData<Unit> = _clickedKakaoNavi

    private var _clickedTmapNavi = SingleLiveEvent<Unit>()
    val clickedTmapNavi : LiveData<Unit> = _clickedTmapNavi

    fun onClickKakaoNavi(){
        _clickedKakaoNavi.call()
    }

    fun onClickTmapNavi(){
        _clickedTmapNavi.call()
    }
}