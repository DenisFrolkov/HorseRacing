package com.denisf.data.database.mappers

import com.denisf.data.database.entity.RaceHistoryEntity
import com.denisf.domain.model.RaceHistory

fun RaceHistory.toEntity() = RaceHistoryEntity(
    id = id,
    date = date,
    winnerId = winnerId,
    horsesCount = horsesCount
)

fun RaceHistoryEntity.toDomain() = RaceHistory(
    id = id,
    date = date,
    winnerId = winnerId,
    horsesCount = horsesCount
)