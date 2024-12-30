package com.jtmcompany.data.model.response.remote

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "items")
data class Item(
    @Element(name="item")
    val items: List<ParkInfo>?
)
