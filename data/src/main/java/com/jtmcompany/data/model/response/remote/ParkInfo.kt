package com.jtmcompany.data.model.response.remote

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class ParkInfo(
    @PropertyElement(name = "prkplceNo")
    var prkplceNo: String?, // 주차장관리번호
    @PropertyElement(name = "prkplceNm")
    var prkplceNm: String?, // 주차장명
    @PropertyElement(name = "prkplceSe")
    var prkplceSe: String?, // 주차장구분
    @PropertyElement(name = "prkplceType")
    var prkplceType: String?, // 주차장유형
    @PropertyElement(name = "rdnmadr")
    var rdnmadr: String?, // 소재지도로명주소
    @PropertyElement(name = "lnmadr")
    var lnmadr: String?, // 소재지지번주소
    @PropertyElement(name = "prkcmprt")
    var prkcmprt: String?, // 주차구획수
    @PropertyElement(name = "feedingSe")
    var feedingSe: String?, // 급지구분
    @PropertyElement(name = "enforceSe")
    var enforceSe: String?, // 부제시행구분
    @PropertyElement(name = "operDay")
    var operDay: String?, // 운영요일
    @PropertyElement(name = "weekdayOperOpenHhmm")
    var weekdayOperOpenHhmm: String?, // 평일운영시작시각
    @PropertyElement(name = "weekdayOperColseHhmm")
    var weekdayOperColseHhmm: String?, // 평일운영종료시각
    @PropertyElement(name = "satOperOperOpenHhmm")
    var satOperOperOpenHhmm: String?, // 토요일운영시작시각
    @PropertyElement(name = "satOperCloseHhmm")
    var satOperCloseHhmm: String?, // 토요일운영종료시각
    @PropertyElement(name = "holidayOperOpenHhmm")
    var holidayOperOpenHhmm: String?, // 공휴일운영시작시각
    @PropertyElement(name = "holidayCloseOpenHhmm")
    var holidayCloseOpenHhmm: String?, // 공휴일운영종료시각
    @PropertyElement(name = "parkingchrgeInfo")
    var parkingchrgeInfo: String?, // 요금정보
    @PropertyElement(name = "basicTime")
    var basicTime: String?, // 주차기본시간
    @PropertyElement(name = "basicCharge")
    var basicCharge: String?, // 주차기본요금
    @PropertyElement(name = "addUnitTime")
    var addUnitTime: String?, // 추가단위시간
    @PropertyElement(name = "addUnitCharge")
    var addUnitCharge: String?, // 추가단위요금
    @PropertyElement(name = "dayCmmtktAdjTime")
    var dayCmmtktAdjTime: String?, // 1일주차권요금적용시간
    @PropertyElement(name = "dayCmmtkt")
    var dayCmmtkt: String?, // 1일주차권요금
    @PropertyElement(name = "monthCmmtkt")
    var monthCmmtkt: String?, // 월정기권요금
    @PropertyElement(name = "metpay")
    var metpay: String?, // 결제방법
    @PropertyElement(name = "spcmnt")
    var spcmnt: String?, // 특기사항
    @PropertyElement(name = "institutionNm")
    var institutionNm: String?, // 관리기관명
    @PropertyElement(name = "phoneNumber")
    var phoneNumber: String?, // 전화번호
    @PropertyElement(name = "latitude")
    var latitude: String?, // 위도
    @PropertyElement(name = "longitude")
    var longitude: String?, // 경도
    @PropertyElement(name = "referenceDate")
    var referenceDate: String?, // 데이터기준일자
    @PropertyElement(name = "instt_code")
    var insttCode: String?, // 제공기관코드
    @PropertyElement(name = "instt_nm")
    var insttNm: String?, // 제공기관기관명
)
