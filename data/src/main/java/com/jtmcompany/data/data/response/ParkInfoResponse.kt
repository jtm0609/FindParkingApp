package com.jtmcompany.data.data.response

import com.google.gson.annotations.SerializedName
import com.jtmcompany.data.data.entity.ParkInfoEntity
import com.jtmcompany.domain.model.ParkInfo

data class ParkInfoResponse( //주자장 시설정보
    @SerializedName("resultCode")
    val resultCode: String ,
    @SerializedName("resultMsg")
    val resultMsg: String,
    @SerializedName("numOfRows")
    val numOfRows: String,
    @SerializedName("pageNo")
    val pageNo: String,
    @SerializedName("totalCount")
    val totalCount: String,
    @SerializedName("PrkSttusInfo")
    val parkInfo: List<ParkInfoEntity>
)
