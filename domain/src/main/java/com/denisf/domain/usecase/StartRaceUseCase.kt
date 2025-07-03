package com.denisf.domain.usecase

import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartRaceUseCase @Inject constructor (
    private val repository: RaceRepository
) {
    operator fun invoke(params: StartRaceParams): Flow<Race> {
        return repository.startRace(params)
    }
}