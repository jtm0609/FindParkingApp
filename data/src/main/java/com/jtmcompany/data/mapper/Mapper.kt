package com.jtmcompany.data.mapper

import com.jtmcompany.data.data.entity.ParkInfoEntity
import com.jtmcompany.data.data.entity.ParkOperInfoEntity
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

    fun mapperToParkInfo(parkInfos: List<ParkInfoEntity>): List<ParkInfo> {
        return parkInfos.map {
            ParkInfo(
                it.id,
                it.name,
                it.address,
                it.latitude,
                it.longtitude,
                it.area
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
}