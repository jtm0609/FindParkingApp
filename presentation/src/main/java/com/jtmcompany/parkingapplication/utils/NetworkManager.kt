package com.jtmcompany.parkingapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

//@ApplicationContext 를 적어주면 힐트가 알아서 컨텍스트를 찾아준다
class NetworkManager @Inject constructor(@ApplicationContext private val context: Context){

    fun checkNetworkState(): Boolean{

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val actNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        // TRANSPORT_WIFI = WIFI 상태 / TRANSPORT_CELLULAR = Mobile 네트워크 상태
        return when{
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            else ->false
        }
    }
}