package com.jtmcompany.data.datasource.local

import com.jtmcompany.data.data.entity.ParkEntity
import com.jtmcompany.data.db.ParkDao
import com.jtmcompany.domain.model.ParkInfo
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ParkLocalDataSourceImpl @Inject constructor(private val parkDao: ParkDao) :
    ParkLocalDataSource {
    override fun insertParks(parks: List<ParkEntity>): Completable =
        parkDao.insetParks(parks)


    override fun getAllParks(): Single<List<ParkEntity>> =
            parkDao.getAllParks()
}
