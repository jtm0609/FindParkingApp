package com.jtmcompany.data.datasource.local

interface PrefDataSource {

    fun saveParkTotalCnt(cnt : Int)
    fun getParkTotalCnt() : Int
}