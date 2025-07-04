package com.denisf.domain.repository

import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRaceRepository : RaceRepository {

    private var raceToEmit: Race? = null

    fun setRaceToEmit(race: Race) {
        this.raceToEmit = race
    }

    override fun startRace(params: StartRaceParams): Flow<Race> = flow {
        raceToEmit?.let {
            emit(it)
        } ?: throw IllegalStateException("FakeRaceRepository: raceToEmit is null")
    }
}
