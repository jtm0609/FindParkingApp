package com.jtmcompany.domain.usecase

import com.jtmcompany.repository.ParkRepository
import javax.inject.Inject

class UpdateParkTotalCntUseCase @Inject constructor(private val repository: ParkRepository) {
    fun execute(totalCnt: Int) {
        repository.saveParkTotalCnt(totalCnt)
    }
}