package com.jtmcompany.parkingapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import com.jtmcompany.parkingapplication.base.BaseViewModel
import com.jtmcompany.parkingapplication.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.math.floor
import kotlin.math.roundToInt

class ParkDetailViewModel : BaseViewModel() {
    private val _clickedGetDirection = SingleLiveEvent<Unit>()
    val clickedGetDirection: LiveData<Unit> = _clickedGetDirection

    fun onClickGetDirection() {
        _clickedGetDirection.call()
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
}