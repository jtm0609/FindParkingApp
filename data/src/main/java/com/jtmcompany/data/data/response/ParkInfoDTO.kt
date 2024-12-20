package com.jtmcompany.data.data.response

import com.jtmcompany.data.data.entity.response.remote.Body
import com.jtmcompany.data.data.entity.response.remote.Header
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class ParkInfoDTO( //주자장 시설정보
    @Element(name = "header")
    val header: Header?,
    @Element(name = "body")
    val body: Body?
)
