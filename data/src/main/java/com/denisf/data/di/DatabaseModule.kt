package com.denisf.data.di

import android.content.Context
import androidx.room.Room
import com.denisf.data.database.AppDatabase
import com.denisf.data.database.dao.RaceHistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "race_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRaceHistoryDao(db: AppDatabase): RaceHistoryDao {
        return db.raceHistoryDao()
    }
}