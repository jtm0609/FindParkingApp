package com.jtmcompany.data.datasource.remote

import com.jtmcompany.data.data.response.ParkInfoResponse
import com.jtmcompany.data.data.response.ParkOperInfoResponse
import io.reactivex.Single

/**
 * Api 호출을 통해 주차장 관련 Data를 가져오기 위한 interface
 * DataSourceInpl 에서 구현된다.
 */
interface ParkRemoteDataSource {
    fun getParkInfo(numOfRows: Int) : Single<ParkInfoResponse>

    fun getParkOperInfo(): Single<ParkOperInfoResponse>
}