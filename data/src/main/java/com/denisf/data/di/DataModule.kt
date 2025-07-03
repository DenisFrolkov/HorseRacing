package com.denisf.data.di

import android.content.Context
import androidx.room.Room
import com.denisf.data.database.AppDatabase
import com.denisf.data.database.dao.RaceHistoryDao
import com.denisf.data.repository.RaceHistoryRepositoryImpl
import com.denisf.data.repository.RaceRepositoryImpl
import com.denisf.domain.repository.RaceHistoryRepository
import com.denisf.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "race_db")
            .build()
    }

    @Provides
    fun provideRaceHistoryDao(db: AppDatabase): RaceHistoryDao {
        return db.raceHistoryDao()
    }

    @Provides
    fun provideRaceRepository(): RaceRepository {
        return RaceRepositoryImpl()
    }

    @Provides
    fun provideRaceHistoryRepository(dao: RaceHistoryDao): RaceHistoryRepository {
        return RaceHistoryRepositoryImpl(dao)
    }
}