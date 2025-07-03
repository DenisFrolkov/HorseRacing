package com.denisf.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.denisf.data.database.entity.RaceHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RaceHistoryEntity)

    @Query("SELECT * FROM race_history ORDER BY date DESC")
    fun getAll(): Flow<List<RaceHistoryEntity>>

    @Query("DELETE FROM race_history")
    suspend fun clear()
}