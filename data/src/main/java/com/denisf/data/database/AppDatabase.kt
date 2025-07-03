package com.denisf.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.denisf.data.database.dao.RaceHistoryDao
import com.denisf.data.database.entity.RaceHistoryEntity

@Database(entities = [RaceHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun raceHistoryDao(): RaceHistoryDao
}