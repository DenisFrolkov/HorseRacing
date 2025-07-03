package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.RaceHistoryRepository

class SaveRaceResultUseCase(
    private val repository: RaceHistoryRepository
) {
    suspend operator fun invoke(item: RaceHistory) {
        repository.save(item)
    }
}