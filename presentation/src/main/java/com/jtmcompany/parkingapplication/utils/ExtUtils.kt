package com.jtmcompany.parkingapplication.utils

import android.location.Location
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.utils.Constants.NONE_DISTANCE

fun ParkInfo.applyDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): ParkInfo {
    if(latitude=="0" || longitude=="0"){
        distance = NONE_DISTANCE.toDouble()
    }else {
        val locationA = Location("point A")
        locationA.latitude = lat1
        locationA.longitude = lng1

        val locationB = Location("point B")
        locationB.latitude = lat2
        locationB.longitude = lng2

        distance = locationA.distanceTo(locationB).toDouble()
    }
    return this
}