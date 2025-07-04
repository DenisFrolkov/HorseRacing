package com.denisf.presentation.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.ISaveRaceResultUseCase

class FakeSaveRaceResultUseCase : ISaveRaceResultUseCase {
    val savedItems = mutableListOf<RaceHistory>()

    override suspend fun invoke(history: RaceHistory) {
        savedItems.add(history)
    }
}

