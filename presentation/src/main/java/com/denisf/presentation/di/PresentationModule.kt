package com.denisf.presentation.di

import com.denisf.domain.usecase.ClearRaceHistoryUseCase
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.domain.usecase.SaveRaceResultUseCase
import com.denisf.domain.usecase.StartRaceUseCase
import dagger.Module
import dagger.Provides

@Module
class PresentationModule() {
    @Provides
    fun provideRaceViewModelFactory(
        startRaceUseCase: StartRaceUseCase,
        saveRaceResultUseCase: SaveRaceResultUseCase
    ): RaceViewModelFactory {
        return RaceViewModelFactory(
            startRaceUseCase = startRaceUseCase,
            saveRaceResultUseCase = saveRaceResultUseCase
        )
    }
    @Provides
    fun provideRHistoryViewModelFactory(
        getRaceHistoryUseCase: GetRaceHistoryUseCase,
        clearRaceHistoryUseCase: ClearRaceHistoryUseCase
    ): HistoryViewModelFactory {
        return HistoryViewModelFactory(
            getRaceHistoryUseCase = getRaceHistoryUseCase,
            clearRaceHistoryUseCase = clearRaceHistoryUseCase
        )
    }
}