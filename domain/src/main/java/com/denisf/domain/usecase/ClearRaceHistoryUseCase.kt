package com.denisf.domain.usecase

import com.denisf.domain.repository.RaceHistoryRepository
import javax.inject.Inject

class ClearRaceHistoryUseCase @Inject constructor(
    private val repository: RaceHistoryRepository
) {
    suspend operator fun invoke() {
        repository.clear()
    }
}