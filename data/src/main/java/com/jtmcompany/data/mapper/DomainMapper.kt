package com.jtmcompany.data.mapper

import com.jtmcompany.data.data.entity.response.local.ParkEntity
import com.jtmcompany.data.data.response.ParkInfoDTO
import com.jtmcompany.domain.model.ParkInfo

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 * @param movies Data Model 로 변환 할 Entity
 * @return Data Model
 */
object DomainMapper {

    fun ParkInfoDTO.toDomainModel(): List<ParkInfo> {
        return body.item.items.map {result ->
            ParkInfo(
                result.prkplceNo, // 주차장관리번호
                result.prkplceNm, // 주차장명
                result.prkplceSe, // 주차장구분
                result.prkplceType, // 주차장유형
                result.rdnmadr, // 소재지도로명주소
                result.lnmadr, // 소재지지번주소
                result.prkcmprt, // 주차구획수
                result.feedingSe, // 급지구분
                result.enforceSe, // 부제시행구분
                result.operDay, // 운영요일
                result.weekdayOperOpenHhmm, // 평일운영시작시
                result.weekdayOperColseHhmm, // 평일운영종료
                result.satOperOperOpenHhmm, // 토요일운영시작
                result.satOperCloseHhmm, // 토요일운영종료시각
                result.holidayOperOpenHhmm, // 공휴일운영시작
                result.holidayCloseOpenHhmm, // 공휴일운영종
                result.parkingchrgeInfo, // 요금정보
                result.basicTime, // 주차기본시간
                result.basicCharge, // 주차기본요금
                result.addUnitTime, // 추가단위시간
                result.addUnitCharge, // 추가단위요금
                result.dayCmmtktAdjTime, // 1일주차권요금적용시
                result.dayCmmtkt, // 1일주차권요금
                result.monthCmmtkt, // 월정기권요금
                result.metpay, // 결제방법
                result.spcmnt, // 특기사항
                result.institutionNm, // 관리기관명
                result.phoneNumber, // 전화번호
                result.latitude.takeIf { !it.isNullOrEmpty()} ?:"0", // 위도
                result.longitude.takeIf {!it.isNullOrEmpty() } ?:"0", // 경도
                result.referenceDate, // 데이터기준일자
                result.instt_code, // 제공기관코드
                result.instt_nm, // 제공기관기관명
                body.totalCount
            )
        }
    }

    fun ParkEntity.toDomainModel() : ParkInfo {
        return ParkInfo(
            prkplceNo, // 주차장관리번호
            prkplceNm, // 주차장명
            prkplceSe, // 주차장구분
            prkplceType, // 주차장유형
            rdnmadr, // 소재지도로명주소
            lnmadr, // 소재지지번주소
            prkcmprt, // 주차구획수
            feedingSe, // 급지구분
            enforceSe, // 부제시행구분
            operDay, // 운영요일
            weekdayOperOpenHhmm, // 평일운영시작시
            weekdayOperColseHhmm, // 평일운영종료
            satOperOperOpenHhmm, // 토요일운영시작
            satOperCloseHhmm, // 토요일운영종료시각
            holidayOperOpenHhmm, // 공휴일운영시작
            holidayCloseOpenHhmm, // 공휴일운영종
            parkingchrgeInfo, // 요금정보
            basicTime, // 주차기본시간
            basicCharge, // 주차기본요금
            addUnitTime, // 추가단위시간
            addUnitCharge, // 추가단위요금
            dayCmmtktAdjTime, // 1일주차권요금적용시
            dayCmmtkt, // 1일주차권요금
            monthCmmtkt, // 월정기권요금
            metpay, // 결제방법
            spcmnt, // 특기사항
            institutionNm, // 관리기관명
            phoneNumber, // 전화번호
            latitude.takeIf { !it.isNullOrEmpty()} ?:"0", // 위도
            longitude.takeIf {!it.isNullOrEmpty()} ?:"0", // 경도
            referenceDate, // 데이터기준일자
            instt_code, // 제공기관코드
            instt_nm, // 제공기관기관명
            totalCount
        )
    }
}