package com.jtmcompany.data.data.entity.response.remote

import com.google.gson.annotations.SerializedName
import com.jtmcompany.domain.model.ParkOperInfo

data class ParkOperInfo(
    @SerializedName("prk_center_id")
    val id: String,
    @SerializedName("Sunday")
    val sunday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Monday")
    val monday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Tuesday")
    val tuesday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Wednesday")
    val wednesday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Thursday")
    val thursday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Friday")
    val friday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Saturday")
    val saturday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("Holiday")
    val holiday: ParkOperInfo.OperationTime.OperationTime,
    @SerializedName("opertn_bs_free_time")
    val defaultFreeTime: String, //기본 무료 시간
    @SerializedName("basic_info")
    val basicInfo: ParkOperInfo.BasicInfo.BasicInfo, //기본 제공 정보
    @SerializedName("fxamt_info")
    val subscribePriceInfo: ParkOperInfo.SubscribePrice.SubscribePrice //정액 요금 정보
)
