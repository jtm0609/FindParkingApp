package com.jtmcompany.domain.usecase

import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.domain.repository.ParkRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetParkTotalCntUseCase @Inject constructor(private val repository: ParkRepository) {
    fun execute(): Int = repository.getParkTotalCnt()
}