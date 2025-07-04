package com.denisf.domain.repository

import com.denisf.domain.model.RaceHistory
import kotlinx.coroutines.flow.Flow

fun interface IGetRaceHistoryUseCase {
    suspend operator fun invoke(): Flow<List<RaceHistory>>
}