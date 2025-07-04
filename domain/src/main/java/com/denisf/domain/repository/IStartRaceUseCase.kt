package com.denisf.domain.repository

import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import kotlinx.coroutines.flow.Flow

fun interface IStartRaceUseCase {
    operator fun invoke(params: StartRaceParams): Flow<Race>
}