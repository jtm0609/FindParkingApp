package com.jtmcompany.data.datasource.remote

import com.jtmcompany.data.api.ApiInterface
import com.jtmcompany.data.data.response.ParkInfoResponse
import com.jtmcompany.data.data.response.ParkOperInfoResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 * Api 를 통해 Data 를 가져오는 것이기 때문에 ApiInterface 를 사용한다.
 **/
class ParkRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    ParkRemoteDataSource{
    override fun getParkInfo(): Single<ParkInfoResponse> {
        return apiInterface.getParkInfoApi()
    }

    override fun getParkOperInfo(): Single<ParkOperInfoResponse> {
        return apiInterface.getParkOperInfoApi()
    }

}