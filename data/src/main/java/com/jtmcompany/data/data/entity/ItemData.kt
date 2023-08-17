package com.jtmcompany.data.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "items")
data class ItemData(
    @Element(name="item")
    val items: List<ParkInfoData>
)
