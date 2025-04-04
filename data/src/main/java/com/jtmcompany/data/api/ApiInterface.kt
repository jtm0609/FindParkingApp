package com.jtmcompany.data.api

import com.jtmcompany.data.model.response.remote.ParkInfoDTO
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
    ): Single<ParkInfoDTO>

}