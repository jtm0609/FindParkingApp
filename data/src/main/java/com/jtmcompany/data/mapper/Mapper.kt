package com.jtmcompany.data.mapper

import com.jtmcompany.data.data.entity.BodyData
import com.jtmcompany.data.data.entity.ParkEntity
import com.jtmcompany.data.data.entity.ParkInfoData
import com.jtmcompany.data.data.entity.ParkOperInfoEntity
import com.jtmcompany.data.data.response.ParkInfoResponse
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 * @param movies Data Model 로 변환 할 Entity
 * @return Data Model
 */
object Mapper {

    fun mapperToParkInfo(parkInfos: BodyData): List<ParkInfo> {
        return parkInfos.items.map {
            ParkInfo(
                it.prkplceNo, // 주차장관리번호
                it.prkplceNm, // 주차장명
                it.prkplceSe, // 주차장구분
                it.prkplceType, // 주차장유형
                it.rdnmadr, // 소재지도로명주소
                it.lnmadr, // 소재지지번주소
                it.prkcmprt, // 주차구획수
                it.feedingSe, // 급지구분
                it.enforceSe, // 부제시행구분
                it.operDay, // 운영요일
                it.weekdayOperOpenHhmm, // 평일운영시작시
                it.weekdayOperColseHhmm, // 평일운영종료
                it.satOperOperOpenHhmm, // 토요일운영시작
                it.satOperCloseHhmm, // 토요일운영종료시각
                it.holidayOperOpenHhmm, // 공휴일운영시작
                it.holidayCloseOpenHhmm, // 공휴일운영종
                it.parkingchrgeInfo, // 요금정보
                it.basicTime, // 주차기본시간
                it.basicCharge, // 주차기본요금
                it.addUnitTime, // 추가단위시간
                it.addUnitCharge, // 추가단위요금
                it.dayCmmtktAdjTime, // 1일주차권요금적용시
                it.dayCmmtkt, // 1일주차권요금
                it.monthCmmtkt, // 월정기권요금
                it.metpay, // 결제방법
                it.spcmnt, // 특기사항
                it.institutionNm, // 관리기관명
                it.phoneNumber, // 전화번호
                it.latitude, // 위도
                it.longitude, // 경도
                it.referenceDate, // 데이터기준일자
                it.instt_code, // 제공기관코드
                it.instt_nm, // 제공기관기관명
                parkInfos.totalCount
            )
        }
    }

    fun mapperToParkOperStatus(parkOperInfos: List<ParkOperInfoEntity>): List<ParkOperInfo> {
        return parkOperInfos.map {
            ParkOperInfo(
                it.id,
                it.sunday,
                it.monday,
                it.tuesday,
                it.wednesday,
                it.thursday,
                it.friday,
                it.saturday,
                it.holiday,
                it.defaultFreeTime,
                it.basicInfo,
                it.subscribePriceInfo
            )
        }
    }

    //DB Entity Class에서 데이터를 불러오기 위해 필요한 function
    fun mapperToLocalParkInfo(parkEntity: List<ParkEntity>): List<ParkInfo> {
        return parkEntity.map {
            ParkInfo(
                it.prkplceNo, // 주차장관리번호
                it.prkplceNm, // 주차장명
                it.prkplceSe, // 주차장구분
                it.prkplceType, // 주차장유형
                it.rdnmadr, // 소재지도로명주소
                it.lnmadr, // 소재지지번주소
                it.prkcmprt, // 주차구획수
                it.feedingSe, // 급지구분
                it.enforceSe, // 부제시행구분
                it.operDay, // 운영요일
                it.weekdayOperOpenHhmm, // 평일운영시작시
                it.weekdayOperColseHhmm, // 평일운영종료
                it.satOperOperOpenHhmm, // 토요일운영시작
                it.satOperCloseHhmm, // 토요일운영종료시각
                it.holidayOperOpenHhmm, // 공휴일운영시작
                it.holidayCloseOpenHhmm, // 공휴일운영종
                it.parkingchrgeInfo, // 요금정보
                it.basicTime, // 주차기본시간
                it.basicCharge, // 주차기본요금
                it.addUnitTime, // 추가단위시간
                it.addUnitCharge, // 추가단위요금
                it.dayCmmtktAdjTime, // 1일주차권요금적용시
                it.dayCmmtkt, // 1일주차권요금
                it.monthCmmtkt, // 월정기권요금
                it.metpay, // 결제방법
                it.spcmnt, // 특기사항
                it.institutionNm, // 관리기관명
                it.phoneNumber, // 전화번호
                it.latitude, // 위도
                it.longitude, // 경도
                it.referenceDate, // 데이터기준일자
                it.instt_code, // 제공기관코드
                it.instt_nm, // 제공기관기관명
                it.totalCount
            )
        }
    }

    //remote API Data Class ->  DB Entity Class
    fun mapperToLocalParkEntity(parkInfo: List<ParkInfo>): List<ParkEntity> {
        return parkInfo.map{
            ParkEntity(
                0,
                it.prkplceNo, // 주차장관리번호
                it.prkplceNm, // 주차장명
                it.prkplceSe, // 주차장구분
                it.prkplceType, // 주차장유형
                it.rdnmadr, // 소재지도로명주소
                it.lnmadr, // 소재지지번주소
                it.prkcmprt, // 주차구획수
                it.feedingSe, // 급지구분
                it.enforceSe, // 부제시행구분
                it.operDay, // 운영요일
                it.weekdayOperOpenHhmm, // 평일운영시작시
                it.weekdayOperColseHhmm, // 평일운영종료
                it.satOperOperOpenHhmm, // 토요일운영시작
                it.satOperCloseHhmm, // 토요일운영종료시각
                it.holidayOperOpenHhmm, // 공휴일운영시작
                it.holidayCloseOpenHhmm, // 공휴일운영종
                it.parkingchrgeInfo, // 요금정보
                it.basicTime, // 주차기본시간
                it.basicCharge, // 주차기본요금
                it.addUnitTime, // 추가단위시간
                it.addUnitCharge, // 추가단위요금
                it.dayCmmtktAdjTime, // 1일주차권요금적용시
                it.dayCmmtkt, // 1일주차권요금
                it.monthCmmtkt, // 월정기권요금
                it.metpay, // 결제방법
                it.spcmnt, // 특기사항
                it.institutionNm, // 관리기관명
                it.phoneNumber, // 전화번호
                it.latitude, // 위도
                it.longitude, // 경도
                it.referenceDate, // 데이터기준일자
                it.instt_code, // 제공기관코드
                it.instt_nm, // 제공기관기관명
                it.totalCnt
            )
        }
    }
}