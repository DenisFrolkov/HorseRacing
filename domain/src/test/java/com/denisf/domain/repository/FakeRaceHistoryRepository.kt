package com.denisf.domain.repository

import com.denisf.domain.model.RaceHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRaceHistoryRepository : RaceHistoryRepository {

    private val items = mutableListOf<RaceHistory>()
    private val itemsFlow = MutableStateFlow<List<RaceHistory>>(emptyList())

    override suspend fun save(item: RaceHistory) {
        items.add(item)
        itemsFlow.value = items.toList()
    }

    override fun getAll(): Flow<List<RaceHistory>> = itemsFlow

    override suspend fun clear() {
        items.clear()
        itemsFlow.value = emptyList()
    }
}
