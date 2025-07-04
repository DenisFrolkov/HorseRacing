package com.denisf.presentation.ui.viewModel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisf.domain.model.Race
import com.denisf.domain.model.RaceHistory
import com.denisf.domain.model.StartRaceParams
import com.denisf.domain.repository.ISaveRaceResultUseCase
import com.denisf.domain.repository.IStartRaceUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RaceViewModel(
    private val startRaceUseCase: IStartRaceUseCase,
    private val saveRaceResultUseCase: ISaveRaceResultUseCase
): ViewModel() {

    var raceStatus = mutableStateOf(RaceStatus.START)

    private val _raceState = MutableStateFlow<Race?>(null)
    val raceState: StateFlow<Race?> = _raceState

    private var raceJob: Job? = null

    var horseCountText = MutableStateFlow("")
        private set

    var raceLengthText = MutableStateFlow("")
        private set

    val horseCount: StateFlow<Int> = horseCountText
        .map { it.toIntOrNull() ?: 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val raceLength: StateFlow<Int> = raceLengthText
        .map { it.toIntOrNull() ?: 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val isValidHorseCount: StateFlow<Boolean> = horseCount
        .map { it in 2..6 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isValidRaceLength: StateFlow<Boolean> = raceLength
        .map { it > 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    @SuppressLint("NewApi")
    fun startRace(horseCount: Int, trackLength: Int) {
        _raceState.value = null
        raceStatus.value = RaceStatus.START

        raceJob = viewModelScope.launch {
            startRaceUseCase(StartRaceParams(horseCount, trackLength)).collect { race ->
                _raceState.value = race
                if (race.isFinished) {
                    raceStatus.value = RaceStatus.FINISHED

                    val historyItem = RaceHistory(
                        date = getCurrentFormattedDateTime(),
                        winnerId = race.winnerId,
                        horsesCount = race.horses.size
                    )

                    saveRaceResultUseCase(historyItem)
                } else {
                    raceStatus.value = RaceStatus.RUNNING
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentFormattedDateTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
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

    fun updateRaceLengthText(value: String) {
        raceLengthText.value = value
    }

    fun clearInput() {
        horseCountText.value = ""
        raceLengthText.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        raceJob?.cancel()
    }
}

enum class RaceStatus {
    START,
    FINISHED,
    RUNNING
}