package com.jtmcompany.data.datasource.local

import android.content.SharedPreferences
import java.lang.reflect.Constructor
import javax.inject.Inject

class PrefDataSourceImpl @Inject constructor (private val sharedPreferences: SharedPreferences)
    : PrefDataSource {

    companion object {
        private const val PARK_TOTAL_CNT = "park_total_cnt"
    }

    override fun saveParkTotalCnt(cnt: Int) {
        sharedPreferences.edit().putInt(PARK_TOTAL_CNT, cnt).apply()
    }


    override fun getParkTotalCnt() : Int {
        return sharedPreferences.getInt(PARK_TOTAL_CNT, 0)
    }


}