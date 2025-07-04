package com.denisf.presentation.usecase

import com.denisf.domain.repository.IClearRaceHistoryUseCase

class FakeClearRaceHistoryUseCase : IClearRaceHistoryUseCase {
    var wasCalled = false

    override suspend fun invoke() {
        wasCalled = true
    }
}