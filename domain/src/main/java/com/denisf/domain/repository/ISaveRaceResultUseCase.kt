package com.denisf.domain.repository

import com.denisf.domain.model.RaceHistory

fun interface ISaveRaceResultUseCase {
    suspend operator fun invoke(history: RaceHistory)
}