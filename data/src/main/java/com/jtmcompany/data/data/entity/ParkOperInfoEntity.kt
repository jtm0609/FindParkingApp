package com.jtmcompany.data.data.entity

import com.google.gson.annotations.SerializedName
import com.jtmcompany.domain.model.ParkOperInfo

data class ParkOperInfoEntity(
    @SerializedName("prk_center_id")
    val id: String,
    @SerializedName("Sunday")
    val sunday: ParkOperInfo.OperationTime,
    @SerializedName("Monday")
    val monday: ParkOperInfo.OperationTime,
    @SerializedName("Tuesday")
    val tuesday: ParkOperInfo.OperationTime,
    @SerializedName("Wednesday")
    val wednesday: ParkOperInfo.OperationTime,
    @SerializedName("Thursday")
    val thursday: ParkOperInfo.OperationTime,
    @SerializedName("Friday")
    val friday: ParkOperInfo.OperationTime,
    @SerializedName("Saturday")
    val saturday: ParkOperInfo.OperationTime,
    @SerializedName("Holiday")
    val holiday: ParkOperInfo.OperationTime,
    @SerializedName("opertn_bs_free_time")
    val defaultFreeTime: String, //기본 무료 시간
    @SerializedName("basic_info")
    val basicInfo: ParkOperInfo.BasicInfo, //기본 제공 정보
    @SerializedName("fxamt_info")
    val subscribePriceInfo: ParkOperInfo.SubscribePrice //정액 요금 정보
)
