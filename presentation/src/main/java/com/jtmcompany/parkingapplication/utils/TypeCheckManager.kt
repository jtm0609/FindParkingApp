package com.jtmcompany.parkingapplication.utils

object TypeCheckManager {

    //숫자 체크
    fun isNumeric(str: String?): Boolean{
        return try {
            str?.toDouble()
            true
        } catch (e: Exception){
            false
        }
    }
}