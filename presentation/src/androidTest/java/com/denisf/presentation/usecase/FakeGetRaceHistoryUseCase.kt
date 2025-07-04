package com.denisf.presentation.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.IGetRaceHistoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGetRaceHistoryUseCase(
    private val itemsToEmit: List<RaceHistory> = emptyList()
) : IGetRaceHistoryUseCase {

    override suspend fun invoke(): Flow<List<RaceHistory>> {
        return flowOf(itemsToEmit)
    }
}
