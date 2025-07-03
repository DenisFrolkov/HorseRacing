package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.RaceHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetRaceHistoryUseCase(
    private val repository: RaceHistoryRepository
) {
    operator fun invoke(): Flow<List<RaceHistory>> {
        return repository.getAll()
    }
}