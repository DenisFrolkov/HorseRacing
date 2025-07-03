package com.denisf.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisf.domain.usecase.GetRaceHistoryUseCase

class HistoryViewModelFactory(
    private val getRaceHistoryUseCase: GetRaceHistoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(getRaceHistoryUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}