package com.jtmcompany.data.data.response

import com.jtmcompany.data.data.entity.BodyData
import com.jtmcompany.data.data.entity.HeaderData
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class ParkInfoResponse( //주자장 시설정보
    //val response: ResponseEntity
    @Element(name = "header")
    val header: HeaderData,
    @Element(name = "body")
    val body: BodyData
)
