package com.denisf.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisf.domain.repository.ISaveRaceResultUseCase
import com.denisf.domain.repository.IStartRaceUseCase
import com.denisf.presentation.ui.viewModel.RaceViewModel

class RaceViewModelFactory(
    val startRaceUseCase: IStartRaceUseCase,
    val saveRaceResultUseCase: ISaveRaceResultUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RaceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RaceViewModel(
                startRaceUseCase,
                saveRaceResultUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}