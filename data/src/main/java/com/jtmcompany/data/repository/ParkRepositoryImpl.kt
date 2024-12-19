package com.jtmcompany.data.repository

import android.util.Log
import com.jtmcompany.data.datasource.local.ParkLocalDataSource
import com.jtmcompany.data.datasource.local.PrefDataSource
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.mapper.DataMapper.toDataModel
import com.jtmcompany.data.mapper.DomainMapper.toDomainModel
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.repository.ParkRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Domain Layer 의 Repository Interface 구현부.
 * DataSource 를 인자로 받아 컨트롤 하여 필요한 Park Data 를 가져옴.
 *
 * @param parkLocalDataSource Local 에 저장 되어있는 Data
 * @param parkRemoteDataSource api 에서 가져오는 Data
 */
class ParkRepositoryImpl @Inject constructor(
    private val parkRemoteDataSource: ParkRemoteDataSource,
    private val parkLocalDataSource: ParkLocalDataSource,
    private val prefDataSource: PrefDataSource
) : ParkRepository {

    override fun getRemoteParkInfo(numOfRows: Int): Flowable<List<ParkInfo>> {

        return parkRemoteDataSource.getParkInfo(numOfRows).toFlowable().doOnNext {
            // API 호출이 성공한 경우 데이터를 출력합니다.
                response ->
            Log.d("tak", "response: $response")
        }.map {
            it.toDomainModel()
        }.doOnNext { mappingData ->
            // 변환된 데이터를 출력합니다.
            Log.d("tak", "mappingData: $mappingData")
            Log.d("tak", "mappingData size: ${mappingData.size}")
        }
    }

    override fun insertPark(parks: List<ParkInfo>): Completable {
        return with(parkLocalDataSource){
            deleteParks().andThen(
                insertParks(
                    parks.map{it.toDataModel()}
                )
            )
        }
    }

    override fun saveParkTotalCnt(cnt: Int) {
        prefDataSource.saveParkTotalCnt(cnt)
    }

    override fun getParkTotalCnt(): Int {
        return prefDataSource.getParkTotalCnt()
    }

    //DB에서 주차장 정보 가져오기
    override fun getLocalParkInfo(): Flowable<List<ParkInfo>> {
        return parkLocalDataSource.getAllParks().toFlowable().map {
            it.map { parkEntity ->  parkEntity.toDomainModel()}
        }
    }
}