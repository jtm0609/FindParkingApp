package com.jtmcompany.parkingapplication.utils

import android.location.Location

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
}