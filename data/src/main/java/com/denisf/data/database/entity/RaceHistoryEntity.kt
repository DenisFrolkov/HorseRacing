package com.denisf.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race_history")
data class RaceHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val winnerId: Int?,
    val horsesCount: Int
)