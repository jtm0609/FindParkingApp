package com.jtmcompany.domain.model

import com.google.gson.annotations.SerializedName

/**
 * 실제 사용하는 Data Class.
 *
 */
data class ParkInfo (
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longtitude: String,
    val area: String
)
