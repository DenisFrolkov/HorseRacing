package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.FakeRaceHistoryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SaveRaceResultUseCaseTest {
    @Test
    fun `saveRaceResultUseCase performs conservation`() = runTest {
        val fakeRepo = FakeRaceHistoryRepository()
        val useCase = SaveRaceResultUseCase(fakeRepo)

        val raceHistory = RaceHistory(
            id = 1,
            date = "04.07.2025",
            winnerId = 3,
            horsesCount = 6
        )

        useCase.invoke(raceHistory)

        val saved = fakeRepo.getAll().first()
        assertEquals(listOf(raceHistory), saved)
    }
}