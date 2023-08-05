package com.jtmcompany.parkingapplication.utils

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    val PREFERENCES_NAME = "jtm"
    private val DEFAULT_VALUE_STRING = ""
    private val DEFAULT_VALUE_BOOLEAN = false
    private val DEFAULT_VALUE_INT = -1

    //객체 생성
    fun getPreferences(context: Context)
            : SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setInt(context: Context, key: String, value: Int) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(context: Context, key: String): Int {
        val prefs = getPreferences(context)
        return prefs.getInt(key, DEFAULT_VALUE_INT)
    }


}