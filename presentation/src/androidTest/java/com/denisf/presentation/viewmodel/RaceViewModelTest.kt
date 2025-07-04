package com.denisf.presentation.viewmodel

import com.denisf.domain.model.Horse
import com.denisf.domain.model.Race
import com.denisf.presentation.MainDispatcherRule
import com.denisf.presentation.ui.viewModel.RaceStatus
import com.denisf.presentation.ui.viewModel.RaceViewModel
import com.denisf.presentation.usecase.FakeSaveRaceResultUseCase
import com.denisf.presentation.usecase.FakeStartRaceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RaceViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeStartRaceUseCase: FakeStartRaceUseCase
    private lateinit var fakeSaveRaceResultUseCase: FakeSaveRaceResultUseCase
    private lateinit var viewModel: RaceViewModel

    @Before
    fun setup() {
        fakeStartRaceUseCase = FakeStartRaceUseCase()
        fakeSaveRaceResultUseCase = FakeSaveRaceResultUseCase()
        viewModel = RaceViewModel(fakeStartRaceUseCase, fakeSaveRaceResultUseCase)
    }

    @Test
    fun updatesHorseCountTextAndRaceLengthTextCorrectly() = runTest {
        viewModel.updateHorseCountText("4")
        viewModel.updateRaceLengthText("10")

        Assert.assertEquals("4", viewModel.horseCountText.value)
        Assert.assertEquals("10", viewModel.raceLengthText.value)

        val horseCountValue = viewModel.horseCount.first { it == 4 }
        val raceLengthValue = viewModel.raceLength.first { it == 10 }

        Assert.assertEquals(4, horseCountValue)
        Assert.assertEquals(10, raceLengthValue)
    }


    @Test
    fun validInputsTriggerIsValidState() = runTest {
        viewModel.updateHorseCountText("3")
        viewModel.updateRaceLengthText("15")

        val horseValid = viewModel.isValidHorseCount.first { it }
        val raceLengthValid = viewModel.isValidRaceLength.first { it }

        Assert.assertTrue(horseValid)
        Assert.assertTrue(raceLengthValid)
    }

    @Test
    fun startRaceEmitsRaceUpdatesStatusAndSavesResult() = runTest {
        val race = Race(
            horses = listOf(Horse(0, "TestHorse 1", 0.5f), Horse(1, "TestHorse 2", 0.49f)),
            trackLength = 5,
            isRunning = false,
            isFinished = true,
            startTime = 0L,
            endTime = 50L,
            winnerId = 0
        )
        fakeStartRaceUseCase.raceToEmit = race

        viewModel.startRace(horseCount = 2, trackLength = 5)

        advanceUntilIdle()

        Assert.assertEquals(RaceStatus.FINISHED, viewModel.raceStatus.value)
        Assert.assertEquals(race, viewModel.raceState.value)
        Assert.assertTrue(fakeSaveRaceResultUseCase.savedItems.isNotEmpty())
    }

    @Test
    fun stopRaceCancelsJobAndResetsStatus() = runTest {
        viewModel.startRace(2, 5)
        viewModel.stopRace()

        Assert.assertEquals(RaceStatus.START, viewModel.raceStatus.value)
    }
}