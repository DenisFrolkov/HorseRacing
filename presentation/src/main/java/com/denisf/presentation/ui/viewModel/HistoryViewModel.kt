package com.denisf.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisf.domain.model.RaceHistory
import com.denisf.domain.usecase.ClearRaceHistoryUseCase
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(
    getRaceHistoryUseCase: GetRaceHistoryUseCase,
    private val clearRaceHistoryUseCase: ClearRaceHistoryUseCase
) : ViewModel() {

    val raceHistory: StateFlow<List<RaceHistory>> = getRaceHistoryUseCase()
        .catch { emit(emptyList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.Lazily,
            initialValue = emptyList()
        )

    fun clearRaceHistory() {
        viewModelScope.launch {
            clearRaceHistoryUseCase()
        }
    }
}