package com.denisf.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisf.domain.usecase.SaveRaceResultUseCase
import com.denisf.domain.usecase.StartRaceUseCase

class RaceViewModelFactory(
    val startRaceUseCase: StartRaceUseCase,
    val saveRaceResultUseCase: SaveRaceResultUseCase,
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