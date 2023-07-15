package com.jtmcompany.data.api

import com.jtmcompany.data.data.response.ParkOperInfoResponse
import com.jtmcompany.data.data.response.ParkInfoResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 *  서버와 통신 할 API 리스트
 */
interface ApiInterface {
    // RxKotlin
    @GET("B553881/Parking/PrkSttusInfo")
    fun getParkInfoApi(): Single<ParkInfoResponse>

    @GET("B553881/Parking/PrkOprInfo")
    fun getParkOperInfoApi(): Single<ParkOperInfoResponse>
}