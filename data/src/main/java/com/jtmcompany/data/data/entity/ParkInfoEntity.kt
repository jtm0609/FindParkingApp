package com.jtmcompany.data.data.entity

import com.google.gson.annotations.SerializedName

data class ParkInfoEntity(
    @SerializedName("prk_center_id")
    val id: Int,
    @SerializedName("prk_plce_nm")
    val name: String,
    @SerializedName("prk_plce_adres")
    val address: String,
    @SerializedName("prk_plce_entrc_la")
    val latitude: String,
    @SerializedName("prk_plce_entrc_lo")
    val longtitude: String,
    @SerializedName("prk_cmprt_co")
    val area: String
)
