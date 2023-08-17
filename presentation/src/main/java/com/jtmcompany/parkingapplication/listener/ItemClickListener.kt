package com.jtmcompany.parkingapplication.listener

import com.jtmcompany.domain.model.ParkInfo

interface ItemClickListener{
    fun onItemClick(parkInfo: ParkInfo)
}