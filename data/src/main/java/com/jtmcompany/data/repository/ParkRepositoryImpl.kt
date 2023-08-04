package com.jtmcompany.data.repository

import android.util.Log
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.mapper.Mapper
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo
import com.jtmcompany.repository.ParkRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.NotificationLite.disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ParkRepositoryImpl @Inject constructor(
    private val parkRemoteDataSource: ParkRemoteDataSource
) : ParkRepository {

    override fun getRemoteParkInfo(): Flowable<List<ParkInfo>> {
/*        val disposable = parkRemoteDataSource.getParkInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    // API 호출이 성공한 경우 데이터를 출력합니다.
                    Log.d("tak", "response: $response")
                }
            ) { throwable ->
                // API 호출이 실패한 경우 에러를 출력합니다.
                Log.d("tak", "error: " + throwable.message)
            }*/

        return parkRemoteDataSource.getParkInfo().toFlowable().doOnNext {
            // API 호출이 성공한 경우 데이터를 출력합니다.
                response ->
            Log.d("tak", "response: $response")
        }.map {
            Mapper.mapperToParkInfo(it.response.body.items)
        }.doOnNext { mappingData ->
            // 변환된 데이터를 출력합니다.
            Log.d("tak", "mappingData: $mappingData")
        }
    }

    override fun getRemoteParkOperInfo(): Flowable<List<ParkOperInfo>> {
        return parkRemoteDataSource.getParkOperInfo().toFlowable().map {
            Mapper.mapperToParkOperStatus(it.parkOperInfo)
        }
    }
}