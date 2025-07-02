package com.denisf.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.usecase.StartRaceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RaceViewModel(
    private val startRaceUseCase: StartRaceUseCase
): ViewModel() {

    var raceStatus = mutableStateOf(RaceStatus.SETTING)

    private val _raceState = MutableStateFlow<Race?>(null)
    val raceState: StateFlow<Race?> = _raceState

    fun startRace(horseCount: Int, trackLength: Int) {
        viewModelScope.launch {
            startRaceUseCase(StartRaceParams(horseCount, trackLength)).collect { race ->
                raceStatus.value = RaceStatus.RACE
                _raceState.value = race
            }
        }
    }
}

enum class RaceStatus {
    RACE,
    SETTING
}