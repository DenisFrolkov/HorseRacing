package com.denisf.domain.usecase

import com.denisf.domain.model.Horse
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class StartRaceUseCase {

    operator fun invoke(params: StartRaceParams): Flow<Race> = flow {
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

        while (true) {
            delay(100L)

            val updatedHorses = horses.map { horse ->
                val increment = Random.nextFloat() * 0.3f + 0.1f
                val newProgress = (progressMap[horse.id] ?: 0f) + increment
                progressMap[horse.id] = newProgress
                horse.copy(progress = newProgress)
            }

            val winner = updatedHorses.firstOrNull { it.progress >= trackLength }

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