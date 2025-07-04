package com.denisf.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.denisf.data.database.AppDatabase
import com.denisf.data.database.dao.RaceHistoryDao
import com.denisf.data.database.entity.RaceHistoryEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RaceHistoryDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: RaceHistoryDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.raceHistoryDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndGetAllHistory() = runTest {
        val race = RaceHistoryEntity(
            date = "2025-07-03",
            winnerId = 2,
            horsesCount = 4
        )

        dao.insert(race)

        val result = dao.getAll().first()
        assertThat(result).hasSize(1)
        assertThat(result[0].winnerId).isEqualTo(2)
        assertThat(result[0].horsesCount).isEqualTo(4)
        assertThat(result[0].date).isEqualTo("2025-07-03")
    }

    @Test
    fun clearHistory_shouldReturnEmptyList() = runTest {
        dao.insert(RaceHistoryEntity(date = "2025-07-03", winnerId = 1, horsesCount = 3))
        dao.clear()

        val result = dao.getAll().first()
        assertThat(result).isEmpty()
    }
}