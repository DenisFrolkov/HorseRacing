package com.denisf.presentation.usecase

import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.repository.IStartRaceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStartRaceUseCase : IStartRaceUseCase {
    var raceToEmit: Race? = null

    override operator fun invoke(params: StartRaceParams): Flow<Race> = flow {
        emit(raceToEmit ?: error("FakeStartRaceUseCase: raceToEmit not set"))
    }
}


