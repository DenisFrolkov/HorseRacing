package com.denisf.data

import com.denisf.data.repository.RaceRepositoryImpl
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RaceRepositoryImplTest {

    private val repository = RaceRepositoryImpl()

    @Test
    fun `race completes and winner is set`() = runTest {
        val horseCount = 4
        val trackLength = 1
        val raceFlow = repository.startRace(StartRaceParams(horseCount, trackLength))

        var lastRace: Race? = null

        raceFlow.collect { race ->
            lastRace = race
            println("update: ${race.horses.map { it.progress }}")
        }

        assertNotNull(lastRace)
        assertTrue(lastRace!!.isFinished)
        assertFalse(lastRace.isRunning)
        assertNotNull(lastRace.winnerId)

        val winnerProgress = lastRace.horses.find { it.id == lastRace.winnerId }?.progress ?: 0f
        assertTrue(winnerProgress >= trackLength)
    }
}
