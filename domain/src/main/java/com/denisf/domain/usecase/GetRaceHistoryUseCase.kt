package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.IGetRaceHistoryUseCase
import com.denisf.domain.repository.RaceHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRaceHistoryUseCase @Inject constructor(
    private val repository: RaceHistoryRepository
) : IGetRaceHistoryUseCase {
    override suspend fun invoke(): Flow<List<RaceHistory>> {
        return repository.getAll()
    }
}