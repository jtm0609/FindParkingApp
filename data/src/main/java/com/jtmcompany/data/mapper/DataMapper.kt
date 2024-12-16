package com.jtmcompany.data.mapper

import com.jtmcompany.data.data.entity.response.local.ParkEntity
import com.jtmcompany.domain.model.ParkInfo

object DataMapper {
    fun ParkInfo.toDataModel() : ParkEntity {
        return ParkEntity(
            0,
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
            latitude, // 위도
            longitude, // 경도
            referenceDate, // 데이터기준일자
            instt_code, // 제공기관코드
            instt_nm, // 제공기관기관명
            totalCnt
        )
    }
}