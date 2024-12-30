package com.jtmcompany.data.model.response.remote

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="header")
data class Header(
    @PropertyElement(name="resultCode")
    var resultCode: String?,
    @PropertyElement(name="resultMsg")
    var resultMsg: String?,
    @PropertyElement(name="type")
    var type: String?
)