package com.denisf.horseracing

import android.content.Context
import androidx.room.Room
import com.denisf.data.database.AppDatabase
import com.denisf.data.repository.RaceHistoryRepositoryImpl
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.domain.usecase.SaveRaceResultUseCase
import com.denisf.domain.usecase.StartRaceUseCase
import com.denisf.presentation.ui.viewModel.HistoryViewModel
import com.denisf.presentation.ui.viewModel.RaceViewModel

object RaceAppDependencies {

    private var dbInstance: AppDatabase? = null

    private fun provideDatabase(context: Context): AppDatabase {
        return dbInstance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "race_db")
                .fallbackToDestructiveMigration()
                .build()
                .also { dbInstance = it }
        }
    }

    fun provideRaceViewModel(context: Context): RaceViewModel {
        val db = provideDatabase(context)
        val dao = db.raceHistoryDao()
        val repository = RaceHistoryRepositoryImpl(dao)
        val saveUseCase = SaveRaceResultUseCase(repository)
        val startUseCase = StartRaceUseCase()

        return RaceViewModel(startUseCase, saveUseCase)
    }

    fun provideHistoryViewModel(context: Context): HistoryViewModel {
        val db = provideDatabase(context)
        val dao = db.raceHistoryDao()
        val repository = RaceHistoryRepositoryImpl(dao)
        val getUseCase = GetRaceHistoryUseCase(repository)

        return HistoryViewModel(getUseCase)
    }
}
