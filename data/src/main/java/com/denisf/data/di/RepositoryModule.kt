package com.denisf.data.di

import com.denisf.data.database.dao.RaceHistoryDao
import com.denisf.data.repository.RaceHistoryRepositoryImpl
import com.denisf.domain.repository.RaceHistoryRepository
import dagger.Provides
import dagger.Module

@Module
class RepositoryModule {

    @Provides
    fun provideRaceHistoryRepository(dao: RaceHistoryDao): RaceHistoryRepository {
        return RaceHistoryRepositoryImpl(dao)
    }
}