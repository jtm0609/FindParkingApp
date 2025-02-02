package com.jtmcompany.parkingapplication.application

import android.app.Application
import com.jtmcompany.parkingapplication.R
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import com.skt.Tmap.TMapTapi
import dagger.hilt.android.HiltAndroidApp


/**
해당 class 는 Manifest 에 선언하여 사용하기 때문에 별다른 호출부는 존재하지 않는다.
 **/
@HiltAndroidApp
class ParkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
        KakaoMapSdk.init(this, getString(R.string.kakao_native_app_key))
        TMapTapi(this).setSKTMapAuthentication(getString(R.string.tmap_native_app_key))
    }
}