package com.denisf.domain.di

import com.denisf.domain.repository.IClearRaceHistoryUseCase
import com.denisf.domain.repository.IGetRaceHistoryUseCase
import com.denisf.domain.repository.ISaveRaceResultUseCase
import com.denisf.domain.repository.IStartRaceUseCase
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
    fun provideStartRaceUseCase(repository: RaceRepository): IStartRaceUseCase {
        return StartRaceUseCase(repository)
    }

    @Provides
    fun provideSaveRaceResultUseCase(repository: RaceHistoryRepository): ISaveRaceResultUseCase {
        return SaveRaceResultUseCase(repository)
    }

    @Provides
    fun provideGetRaceHistoryUseCase(repository: RaceHistoryRepository): IGetRaceHistoryUseCase {
        return GetRaceHistoryUseCase(repository)
    }

    @Provides
    fun provideClearRaceHistoryUseCase(repository: RaceHistoryRepository): IClearRaceHistoryUseCase {
        return ClearRaceHistoryUseCase(repository)
    }
}