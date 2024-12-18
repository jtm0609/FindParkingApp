package com.jtmcompany.domain.usecase

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.repository.ParkRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetLocalParkInfoUseCase @Inject constructor(private val repository: ParkRepository){
    operator fun invoke(): Flowable<List<ParkInfo>> = repository.getLocalParkInfo()

}