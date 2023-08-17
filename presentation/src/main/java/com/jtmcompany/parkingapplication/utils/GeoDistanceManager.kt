package com.jtmcompany.parkingapplication.utils

import android.location.Location
import kotlin.math.floor
import kotlin.math.roundToInt

object GeoDistanceManager {

    //거리 계산
    fun getDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        var distance: Double

        val locationA = Location("point A")
        locationA.latitude = lat1
        locationA.longitude = lng1

        val locationB = Location("point B")
        locationB.latitude = lat2
        locationB.longitude = lng2

        distance = locationA.distanceTo(locationB).toDouble()

        return distance
    }

    //계산한 거리 단위(km, m)로 변환
    fun getDistanceStr(distance: Double): String {
        var distanceStr: String

        if (distance > 1000) { //1km 이상
            distanceStr = (distance / 1000).roundToInt().toString() + "km"
        } else { //소수점 버림
            distanceStr = floor(distance).toString() + "m"
        }
        return distanceStr
    }
}