package com.jtmcompany.data.datasource.local

import com.jtmcompany.data.data.entity.ParkEntity
import com.jtmcompany.domain.model.ParkInfo
import io.reactivex.Completable
import io.reactivex.Single

interface ParkLocalDataSource {

    fun insertParks(parks: List<ParkEntity>) : Completable
    fun getAllParks(): Single<List<ParkEntity>>
}