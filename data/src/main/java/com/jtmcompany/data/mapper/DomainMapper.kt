package com.jtmcompany.data.mapper

import com.jtmcompany.data.model.response.local.ParkEntity
import com.jtmcompany.data.model.response.remote.ParkInfoDTO
import com.jtmcompany.domain.model.ParkInfo

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 * @param movies Data Model 로 변환 할 Entity
 * @return Data Model
 */

fun ParkInfoDTO.toDomainModel(): List<ParkInfo> {
    return body?.item?.items?.map { result ->
        ParkInfo(
            result.prkplceNo ?: "null", // 주차장관리번호
            result.prkplceNm ?: "null", // 주차장명
            result.prkplceSe ?: "null", // 주차장구분
            result.prkplceType ?: "null", // 주차장유형
            result.rdnmadr ?: "null", // 소재지도로명주소
            result.lnmadr ?: "null", // 소재지지번주소
            result.prkcmprt ?: "null", // 주차구획수
            result.feedingSe ?: "null", // 급지구분
            result.enforceSe ?: "null", // 부제시행구분
            result.operDay ?: "null", // 운영요일
            result.weekdayOperOpenHhmm ?: "null", // 평일운영시작시
            result.weekdayOperColseHhmm ?: "null", // 평일운영종료
            result.satOperOperOpenHhmm ?: "null", // 토요일운영시작
            result.satOperCloseHhmm ?: "null", // 토요일운영종료시각
            result.holidayOperOpenHhmm ?: "null", // 공휴일운영시작
            result.holidayCloseOpenHhmm ?: "null", // 공휴일운영종
            result.parkingchrgeInfo ?: "null", // 요금정보
            result.basicTime ?: "null", // 주차기본시간
            result.basicCharge ?: "null", // 주차기본요금
            result.addUnitTime ?: "null", // 추가단위시간
            result.addUnitCharge ?: "null", // 추가단위요금
            result.dayCmmtktAdjTime ?: "null", // 1일주차권요금적용시
            result.dayCmmtkt ?: "null", // 1일주차권요금
            result.monthCmmtkt ?: "null", // 월정기권요금
            result.metpay ?: "null", // 결제방법
            result.spcmnt ?: "null", // 특기사항
            result.institutionNm ?: "null", // 관리기관명
            result.phoneNumber ?: "null", // 전화번호
            result.latitude.takeIf { !it.isNullOrEmpty() } ?: "0", // 위도
            result.longitude.takeIf { !it.isNullOrEmpty() } ?: "0", // 경도
            result.referenceDate ?: "null", // 데이터기준일자
            result.insttCode ?: "null", // 제공기관코드
            result.insttNm ?: "null", // 제공기관기관명
            body.totalCount ?: "null"
        )
    } ?: emptyList()
}

fun ParkEntity.toDomainModel(): ParkInfo {
    return ParkInfo(
        prkplceNo ?: "null", // 주차장관리번호
        prkplceNm ?: "null", // 주차장명
        prkplceSe ?: "null", // 주차장구분
        prkplceType ?: "null", // 주차장유형
        rdnmadr ?: "null", // 소재지도로명주소
        lnmadr ?: "null", // 소재지지번주소
        prkcmprt ?: "null", // 주차구획수
        feedingSe ?: "null", // 급지구분
        enforceSe ?: "null", // 부제시행구분
        operDay ?: "null", // 운영요일
        weekdayOperOpenHhmm ?: "null", // 평일운영시작시
        weekdayOperColseHhmm ?: "null", // 평일운영종료
        satOperOperOpenHhmm ?: "null", // 토요일운영시작
        satOperCloseHhmm ?: "null", // 토요일운영종료시각
        holidayOperOpenHhmm ?: "null", // 공휴일운영시작
        holidayCloseOpenHhmm ?: "null", // 공휴일운영종
        parkingchrgeInfo ?: "null", // 요금정보
        basicTime ?: "null", // 주차기본시간
        basicCharge ?: "null", // 주차기본요금
        addUnitTime ?: "null", // 추가단위시간
        addUnitCharge ?: "null", // 추가단위요금
        dayCmmtktAdjTime ?: "null", // 1일주차권요금적용시
        dayCmmtkt ?: "null", // 1일주차권요금
        monthCmmtkt ?: "null", // 월정기권요금
        metpay ?: "null", // 결제방법
        spcmnt ?: "null", // 특기사항
        institutionNm ?: "null", // 관리기관명
        phoneNumber ?: "null", // 전화번호
        latitude.takeIf { !it.isNullOrEmpty() } ?: "0", // 위도
        longitude.takeIf { !it.isNullOrEmpty() } ?: "0", // 경도
        referenceDate ?: "null", // 데이터기준일자
        insttCode ?: "null", // 제공기관코드
        insttNm ?: "null", // 제공기관기관명
        totalCount ?: "null"
    )
}