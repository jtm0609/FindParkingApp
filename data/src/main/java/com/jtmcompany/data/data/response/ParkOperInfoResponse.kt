package com.jtmcompany.data.data.response

import com.google.gson.annotations.SerializedName
import com.jtmcompany.data.data.entity.ParkOperInfoEntity

data class ParkOperInfoResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String,
    @SerializedName("numOfRows")
    val numOfRows: String,
    @SerializedName("pageNo")
    val pageNo: String,
    @SerializedName("totalCount")
    val totalCount: String,
    @SerializedName("PrkOprInfo")
    val parkOperInfo: List<ParkOperInfoEntity> //주차장 운영 정보
    )
