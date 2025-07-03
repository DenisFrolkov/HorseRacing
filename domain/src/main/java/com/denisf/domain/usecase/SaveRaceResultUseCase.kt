package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.RaceHistoryRepository
import javax.inject.Inject

class SaveRaceResultUseCase @Inject constructor(
    private val repository: RaceHistoryRepository
) {
    suspend operator fun invoke(item: RaceHistory) {
        repository.save(item)
    }
}