package com.denisf.data.database.mappers

import com.denisf.data.database.entity.RaceHistoryEntity
import com.denisf.domain.model.RaceHistory

object Mappers {
    fun RaceHistory.toEntity() = RaceHistoryEntity(
        id = id,
        date = date,
        winner = winner,
        horsesCount = horsesCount
    )

    fun RaceHistoryEntity.toDomain() = RaceHistory(
        id = id,
        date = date,
        winner = winner,
        horsesCount = horsesCount
    )
}