package com.denisf.domain.usecase

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.ISaveRaceResultUseCase
import com.denisf.domain.repository.RaceHistoryRepository
import javax.inject.Inject

class SaveRaceResultUseCase @Inject constructor(
    private val repository: RaceHistoryRepository
) : ISaveRaceResultUseCase {
    override suspend operator fun invoke(history: RaceHistory) {
        repository.save(history)
    }
}