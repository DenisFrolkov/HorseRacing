package com.denisf.domain.repository

import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun startRace(params: StartRaceParams): Flow<Race>
}