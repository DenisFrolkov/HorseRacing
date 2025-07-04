package com.denisf.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisf.domain.model.RaceHistory
import com.denisf.domain.repository.IClearRaceHistoryUseCase
import com.denisf.domain.repository.IGetRaceHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(

    private val getRaceHistoryUseCase: IGetRaceHistoryUseCase,
    private val clearRaceHistoryUseCase: IClearRaceHistoryUseCase
) : ViewModel() {

    private val _raceHistory = MutableStateFlow<List<RaceHistory>>(emptyList())
    val raceHistory: StateFlow<List<RaceHistory>> = _raceHistory

    init {
        viewModelScope.launch {
            try {
                getRaceHistoryUseCase()
                    .collect { _raceHistory.value = it }
            } catch (e: Exception) {
                _raceHistory.value = emptyList()
            }
        }
    }

    fun clearRaceHistory() {
        viewModelScope.launch {
            clearRaceHistoryUseCase()
        }
    }
}