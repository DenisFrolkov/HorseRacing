package com.denisf.data.repository

import com.denisf.domain.model.Horse
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.repository.RaceRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.random.Random

class RaceRepositoryImpl() : RaceRepository {

    companion object {
        private const val FRAME_DELAY_MS = 20L
        private const val MIN_STEP = 0.01f
        private const val MAX_STEP = 0.03f
    }

    override fun startRace(params: StartRaceParams): Flow<Race> = flow {
        val horseCount = params.horseCount.coerceIn(2, 6)
        val trackLength = params.trackLength.coerceAtLeast(1)

        val horses = List(horseCount) { index ->
            Horse(
                id = index,
                name = "Horse #${index + 1}",
                progress = 0f
            )
        }

        var race = Race(
            horses = horses,
            trackLength = trackLength,
            isRunning = true,
            isFinished = false,
            startTime = System.currentTimeMillis()
        )

        emit(race)

        val progressMap = horses.associate { it.id to 0f }.toMutableMap()

        while (currentCoroutineContext().isActive) {
            delay(FRAME_DELAY_MS)

            val updatedHorses = race.horses.map { horse ->
                val increment = Random.nextFloat() * MAX_STEP + MIN_STEP
                val newProgress = (progressMap[horse.id] ?: 0f) + increment
                progressMap[horse.id] = newProgress
                horse.copy(progress = newProgress)
            }

            val winner = updatedHorses
                .filter { it.progress >= trackLength }
                .maxByOrNull { it.progress }

            race = if (winner != null) {
                race.copy(
                    horses = updatedHorses,
                    isRunning = false,
                    isFinished = true,
                    winnerId = winner.id,
                    endTime = System.currentTimeMillis()
                )
            } else {
                race.copy(
                    horses = updatedHorses
                )
            }

            emit(race)
            if (race.isFinished) break
        }
    }
}