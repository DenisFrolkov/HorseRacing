package com.denisf.presentation.viewmodel

import com.denisf.domain.model.RaceHistory
import com.denisf.domain.usecase.GetRaceHistoryUseCase
import com.denisf.presentation.MainDispatcherRule
import com.denisf.presentation.repository.FakeRaceHistoryRepository
import com.denisf.presentation.ui.viewModel.HistoryViewModel
import com.denisf.presentation.usecase.FakeClearRaceHistoryUseCase
import com.denisf.presentation.usecase.FakeGetRaceHistoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HistoryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeRaceHistoryRepository: FakeRaceHistoryRepository
    private lateinit var fakeGetRaceHistoryUseCase: GetRaceHistoryUseCase
    private lateinit var fakeClearRaceHistoryUseCase: FakeClearRaceHistoryUseCase
    private lateinit var viewModel: HistoryViewModel

    @Before
    fun setup() {
        fakeRaceHistoryRepository = FakeRaceHistoryRepository()
        fakeGetRaceHistoryUseCase = GetRaceHistoryUseCase(fakeRaceHistoryRepository)
        fakeClearRaceHistoryUseCase = FakeClearRaceHistoryUseCase()
        viewModel = HistoryViewModel(fakeGetRaceHistoryUseCase, fakeClearRaceHistoryUseCase)
    }

    @Test
    fun invokeReturnsListRaceHistory() = runTest {
        val race1 = RaceHistory(id = 1, date = "2025-07-04", winnerId = 2, horsesCount = 5)
        val race2 = RaceHistory(id = 2, date = "2025-07-03", winnerId = 1, horsesCount = 4)

        fakeRaceHistoryRepository.save(race1)
        fakeRaceHistoryRepository.save(race2)

        val result = fakeGetRaceHistoryUseCase().first()

        assertEquals(listOf(race1, race2), result)
    }

    @Test
    fun raceHistoryCollected() = runTest {
        val testHistory = listOf(
            RaceHistory(id = 1, date = "2025-07-04", winnerId = 2, horsesCount = 5)
        )
        val fakeUseCase = FakeGetRaceHistoryUseCase(testHistory)
        val fakeClearUseCase = FakeClearRaceHistoryUseCase()

        val viewModel = HistoryViewModel(fakeUseCase, fakeClearUseCase)

        val result = viewModel.raceHistory.first()

        assertEquals(emptyList<RaceHistory>(), result)
    }

}