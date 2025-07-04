package com.denisf.domain.usecase

import com.denisf.domain.model.Horse
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.repository.FakeRaceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StartRaceUseCaseTest {

    @Test
    fun `startRaceUseCase returns finished race`() = runTest {
        val fakeRepo = FakeRaceRepository()

        val testRace = Race(
            horses = listOf(
                Horse(id = 0, name = "TestHorse1", progress = 3f),
                Horse(id = 1, name = "TestHorse2", progress = 2.8f),
            ),
            trackLength = 3,
            isRunning = false,
            isFinished = true,
            startTime = 0L,
            endTime = 3000L,
            winnerId = 0
        )

        fakeRepo.setRaceToEmit(testRace)

        val useCase = StartRaceUseCase(fakeRepo)

        val result = useCase(StartRaceParams(horseCount = 2, trackLength = 3)).first()

        assertTrue(result.isFinished)
        assertTrue(!result.isFinished == false)
        assertEquals(0, result.winnerId)
    }
}

