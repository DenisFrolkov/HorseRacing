package com.denisf.domain.usecase

import com.denisf.domain.repository.IClearRaceHistoryUseCase
import com.denisf.domain.repository.RaceHistoryRepository
import javax.inject.Inject

class ClearRaceHistoryUseCase @Inject constructor(
    private val repository: RaceHistoryRepository
) : IClearRaceHistoryUseCase {
    override suspend operator fun invoke() {
        repository.clear()
    }
}