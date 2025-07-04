package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.FakeRaceHistoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetRaceHistoryUseCaseTest {

    @Test
    fun `getRaceHistoryUseCase returns all saved RaceHistory items`() = runTest {
        val fakeRepo = FakeRaceHistoryRepository()
        val useCase = GetRaceHistoryUseCase(fakeRepo)

        val raceHistoryOne = RaceHistory(
            id = 1,
            date = "04.07.2025",
            winnerId = 3,
            horsesCount = 6
        )

        val raceHistoryTwo = RaceHistory(
            id = 2,
            date = "05.07.2025",
            winnerId = 4,
            horsesCount = 5
        )

        fakeRepo.save(raceHistoryOne)
        fakeRepo.save(raceHistoryTwo)

        val allRaceHistory = useCase().first()

        assertEquals(listOf(raceHistoryOne, raceHistoryTwo), allRaceHistory)
    }
}
