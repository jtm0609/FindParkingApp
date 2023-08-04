package com.jtmcompany.data.data.response

import com.google.gson.annotations.SerializedName
import com.jtmcompany.data.data.entity.BodyEntity
import com.jtmcompany.data.data.entity.HeaderEntity
import com.jtmcompany.data.data.entity.ParkInfoEntity
import com.jtmcompany.data.data.entity.ResponseEntity
import com.jtmcompany.domain.model.ParkInfo

data class ParkInfoResponse( //주자장 시설정보
    val response: ResponseEntity
)
