package com.jtmcompany.data.repository

import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.mapper.Mapper
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo
import com.jtmcompany.repository.ParkRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ParkRepositoryImpl @Inject constructor(
    private val parkRemoteDataSource: ParkRemoteDataSource
): ParkRepository{

    override fun getRemoteParkInfo(): Flowable<List<ParkInfo>> {
        return parkRemoteDataSource.getParkInfo().toFlowable().map{
            Mapper.mapperToParkInfo(it.parkInfo)
        }
    }

    override fun getRemoteParkOperInfo(): Flowable<List<ParkOperInfo>> {
        return parkRemoteDataSource.getParkOperInfo().toFlowable().map{
            Mapper.mapperToParkOperStatus(it.parkOperInfo)
        }
    }
}