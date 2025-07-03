package com.denisf.domain.repository

import com.denisf.domain.model.RaceHistory
import kotlinx.coroutines.flow.Flow

interface RaceHistoryRepository {
    suspend fun save(item: RaceHistory)
    fun getAll(): Flow<List<RaceHistory>>
    suspend fun clear()
}