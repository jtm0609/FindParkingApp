package com.jtmcompany.data.datasource.local

import com.jtmcompany.data.data.entity.response.local.ParkEntity
import io.reactivex.Completable
import io.reactivex.Single

interface ParkLocalDataSource {

    fun insertParks(parks: List<ParkEntity>) : Completable
    fun getAllParks(): Single<List<ParkEntity>>
    fun deleteParks() : Completable
}