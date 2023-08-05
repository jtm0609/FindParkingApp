package com.jtmcompany.repository

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.model.ParkOperInfo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * UseCase 에 필요한 Interface 를 선한한 Repository.
 * RepositoryImpl 에서 구현되며, 실제 필요한 데이터를 가져온다.
 */
interface ParkRepository {
    fun getRemoteParkInfo(numOfRows: Int): Flowable<List<ParkInfo>>

    fun getRemoteParkOperInfo(): Flowable<List<ParkOperInfo>>

    fun getLocalParkInfo(): Flowable<List<ParkInfo>>

    fun insertPark(parks: List<ParkInfo>): Completable
}