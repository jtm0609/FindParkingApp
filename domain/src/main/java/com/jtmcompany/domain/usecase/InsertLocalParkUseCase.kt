package com.jtmcompany.domain.usecase

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.repository.ParkRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertLocalParkUseCase @Inject constructor(private val repository: ParkRepository){

    fun execute(parks: List<ParkInfo>) : Completable{
        return repository.insertPark(parks)
    }
}