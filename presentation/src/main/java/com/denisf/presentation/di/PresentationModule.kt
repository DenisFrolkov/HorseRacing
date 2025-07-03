package com.denisf.presentation.di

import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.domain.usecase.SaveRaceResultUseCase
import com.denisf.domain.usecase.StartRaceUseCase
import com.denisf.presentation.ui.viewModel.HistoryViewModelFactory
import com.denisf.presentation.ui.viewModel.RaceViewModelFactory
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
        getRaceHistoryUseCase: GetRaceHistoryUseCase
    ): HistoryViewModelFactory {
        return HistoryViewModelFactory(
            getRaceHistoryUseCase = getRaceHistoryUseCase,
        )
    }
}