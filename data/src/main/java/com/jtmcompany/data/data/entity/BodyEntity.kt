package com.jtmcompany.data.data.entity

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo

data class BodyEntity(
    val totalCount: String,
    val numOfRows: String,
    val pageNo: String,
    val items: List<ParkInfoEntity>
)