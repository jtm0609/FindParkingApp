package com.jtmcompany.data.datasource.remote

import android.content.Context
import com.jtmcompany.data.R
import com.jtmcompany.data.api.ApiInterface
import com.jtmcompany.data.data.response.ParkInfoDTO
import io.reactivex.Single
import javax.inject.Inject

/**
 * DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 * Api 를 통해 Data 를 가져오는 것이기 때문에 ApiInterface 를 사용한다.
 **/
class ParkRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val context: Context
) :
    ParkRemoteDataSource {
    override fun getParkInfo(numOfRows: Int): Single<ParkInfoDTO> {
        return apiInterface.getParkInfoApi(context.getString(R.string.api_key),1,numOfRows)
    }
}