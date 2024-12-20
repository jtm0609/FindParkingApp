package com.jtmcompany.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * 실제 사용하는 Data Class.
 *
 */
data class ParkInfo (
    var prkplceNo: String, // 주차장관리번호
    var prkplceNm: String, // 주차장명
    var prkplceSe: String, // 주차장구분
    var prkplceType: String, // 주차장유형
    var rdnmadr: String, // 소재지도로명주소
    var lnmadr: String, // 소재지지번주소
    var prkcmprt: String, // 주차구획수
    var feedingSe: String, // 급지구분
    var enforceSe: String, // 부제시행구분
    var operDay: String, // 운영요일
    var weekdayOperOpenHhmm: String, // 평일운영시작시각
    var weekdayOperColseHhmm: String, // 평일운영종료시각
    var satOperOperOpenHhmm: String, // 토요일운영시작시각
    var satOperCloseHhmm: String, // 토요일운영종료시각
    var holidayOperOpenHhmm: String, // 공휴일운영시작시각
    var holidayCloseOpenHhmm: String, // 공휴일운영종료시각
    var parkingchrgeInfo: String, // 요금정보
    var basicTime: String, // 주차기본시간
    var basicCharge: String, // 주차기본요금
    var addUnitTime: String, // 추가단위시간
    var addUnitCharge: String, // 추가단위요금
    var dayCmmtktAdjTime: String, // 1일주차권요금적용시간
    var dayCmmtkt: String, // 1일주차권요금
    var monthCmmtkt: String, // 월정기권요금
    var metpay: String, // 결제방법
    var spcmnt: String, // 특기사항
    var institutionNm: String, // 관리기관명
    var phoneNumber: String, // 전화번호
    var latitude: String, // 위도
    var longitude: String, // 경도
    var referenceDate: String, // 데이터기준일자
    var instt_code: String, // 제공기관코드
    var instt_nm: String?, // 제공기관기관명

    var totalCnt: String,
    var distance: Double = 0.0 // 나와의 거리
): Serializable {
    val address: String
        get() {
            return rdnmadr.ifEmpty { lnmadr }
        }
}
