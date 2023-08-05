package com.jtmcompany.data.data.entity

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="header")
data class HeaderData(
    @PropertyElement(name="resultCode")
    var resultCode: String,
    @PropertyElement(name="resultMsg")
    var resultMsg: String,
    @PropertyElement(name="type")
    var type: String?
)