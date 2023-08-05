package com.jtmcompany.data.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "body")
data class BodyData(
    @PropertyElement(name="totalCount")
    val totalCount: String,
    @PropertyElement(name="numOfRows")
    val numOfRows: String,
    @PropertyElement(name="pageNo")
    val pageNo: String,
    @Element(name="items")
    val items: List<ParkInfoData>
)