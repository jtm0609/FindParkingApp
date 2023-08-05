package com.jtmcompany.data.api

import com.jtmcompany.data.data.response.ParkOperInfoResponse
import com.jtmcompany.data.data.response.ParkInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  서버와 통신 할 API 리스트
 */
interface ApiInterface {
    // RxKotlin
    @GET("tn_pubr_prkplce_info_api")
    fun getParkInfoApi(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
    ): Single<ParkInfoResponse>


    @GET("PrkOprInfo")
    fun getParkOperInfoApi(): Single<ParkOperInfoResponse>
}