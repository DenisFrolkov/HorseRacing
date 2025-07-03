package com.denisf.data.database.repository

import com.denisf.data.database.dao.RaceHistoryDao
import com.denisf.data.database.mappers.Mappers.toDomain
import com.denisf.data.database.mappers.Mappers.toEntity
import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.RaceHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RaceHistoryRepositoryImpl(
    private val dao: RaceHistoryDao
) : RaceHistoryRepository {

    override suspend fun save(item: RaceHistory) {
        dao.insert(item.toEntity())
    }

    override fun getAll(): Flow<List<RaceHistory>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun clear() = dao.clear()
}