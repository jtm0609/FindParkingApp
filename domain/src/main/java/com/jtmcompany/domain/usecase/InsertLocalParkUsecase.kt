package com.jtmcompany.domain.usecase

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.repository.ParkRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class InsertLocalParkUsecase @Inject constructor(private val repository: ParkRepository){

    fun execute(parks: List<ParkInfo>) : Completable{
        return repository.insertPark(parks)
    }
}