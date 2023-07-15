package com.jtmcompany.domain.model

data class ParkOperInfo(
    //주차장 운영 정보
    val id: String,
    val sunday: OperationTime,
    val monday: OperationTime,
    val tuesday: OperationTime,
    val wednesday: OperationTime,
    val thursday: OperationTime,
    val friday: OperationTime,
    val saturday: OperationTime,
    val holiday: OperationTime,
    val defaultFreeTime: String, //기본 무료 시간
    val basicInfo: BasicInfo, //기본 제공 정보
    val subscribePriceInfo: SubscribePrice //정액 요금 정보
){
    data class BasicInfo(
        val basicTime: String, //기본 시간
        val basicPrice: String, //기본 요금
        val addUnitTime: String, //추가 단위 시간
        val addUnitPrice: String,//추가 단위 요금
    )

    data class OperationTime(
        val startTime: String,
        val endTime: String
    )

    data class SubscribePrice( //정액 요금
        val dailyPrice: String, //1일 요금
        val monthlyPrice: String //월정액
    )
}
