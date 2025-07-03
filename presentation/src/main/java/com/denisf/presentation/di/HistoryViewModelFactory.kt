package com.denisf.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisf.domain.usecase.ClearRaceHistoryUseCase
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.presentation.ui.viewModel.HistoryViewModel

class HistoryViewModelFactory(
    private val getRaceHistoryUseCase: GetRaceHistoryUseCase,
    private val clearRaceHistoryUseCase: ClearRaceHistoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(getRaceHistoryUseCase, clearRaceHistoryUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}