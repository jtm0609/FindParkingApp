package com.jtmcompany.data.model.response.remote

import com.jtmcompany.data.model.response.remote.Body
import com.jtmcompany.data.model.response.remote.Header
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class ParkInfoDTO( //주자장 시설정보
    @Element(name = "header")
    val header: Header?,
    @Element(name = "body")
    val body: Body?
)
