package com.jtmcompany.parkingapplication.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
해당 class 는 Manifest 에 선언하여 사용하기 때문에 별다른 호출부는 존재하지 않는다.
**/
@HiltAndroidApp
class DiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}