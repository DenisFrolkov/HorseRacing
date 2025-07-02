package com.denisf.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisf.domain.model.Race
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.usecase.StartRaceUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RaceViewModel(
    private val startRaceUseCase: StartRaceUseCase
): ViewModel() {

    var raceStatus = mutableStateOf(RaceStatus.START)

    private val _raceState = MutableStateFlow<Race?>(null)
    val raceState: StateFlow<Race?> = _raceState

    private var raceJob: Job? = null

    // Вводимые значения
    var horseCountText = MutableStateFlow("")
        private set

    var raceLengthText = MutableStateFlow("")
        private set

    // Вычисляемые значения
    val horseCount: StateFlow<Int> = horseCountText
        .map { it.toIntOrNull() ?: 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val raceLength: StateFlow<Int> = raceLengthText
        .map { it.toIntOrNull() ?: 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    // Валидация
    val isValidHorseCount: StateFlow<Boolean> = horseCount
        .map { it in 2..6 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isValidRaceLength: StateFlow<Boolean> = raceLength
        .map { it > 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun startRace(horseCount: Int, trackLength: Int) {
        _raceState.value = null
        raceStatus.value = RaceStatus.START

        raceJob = viewModelScope.launch {
            startRaceUseCase(StartRaceParams(horseCount, trackLength)).collect { race ->
                _raceState.value = race
                if (race.isFinished) {
                    raceStatus.value = RaceStatus.FINISHED
                } else {
                    raceStatus.value = RaceStatus.RUNNING
                }
            }
        }
    }

    fun stopRace() {
        raceJob?.cancel()
        raceStatus.value = RaceStatus.START
    }

    fun updateRaceStatus(status: RaceStatus) {
        raceStatus.value = status
    }

    fun updateHorseCountText(value: String) {
        horseCountText.value = value
    }

    fun updaterRaceLengthText(value: String) {
        raceLengthText.value = value
    }

    fun clearInput() {
        horseCountText.value = ""
        raceLengthText.value = ""
    }
}

enum class RaceStatus {
    START,
    FINISHED,
    RUNNING
}