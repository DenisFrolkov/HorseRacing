package com.denisf.domain.di

import com.denisf.domain.repository.RaceHistoryRepository
import com.denisf.domain.repository.RaceRepository
import com.denisf.domain.usecase.ClearRaceHistoryUseCase
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.domain.usecase.SaveRaceResultUseCase
import com.denisf.domain.usecase.StartRaceUseCase
import dagger.Provides
import dagger.Module

@Module
class DomainModule {

    @Provides
    fun provideStartRaceUseCase(repository: RaceRepository): StartRaceUseCase {
        return StartRaceUseCase(repository)
    }

    @Provides
    fun provideSaveRaceResultUseCase(repository: RaceHistoryRepository): SaveRaceResultUseCase {
        return SaveRaceResultUseCase(repository)
    }

    @Provides
    fun provideGetRaceHistoryUseCase(repository: RaceHistoryRepository): GetRaceHistoryUseCase {
        return GetRaceHistoryUseCase(repository)
    }

    @Provides
    fun provideClearRaceHistoryUseCase(repository: RaceHistoryRepository): ClearRaceHistoryUseCase {
        return ClearRaceHistoryUseCase(repository)
    }
}