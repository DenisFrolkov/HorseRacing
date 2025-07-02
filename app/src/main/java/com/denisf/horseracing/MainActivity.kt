package com.denisf.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.denisf.domain.usecase.StartRaceUseCase
import com.denisf.horseracing.ui.theme.HorseRacingTheme
import com.denisf.presentation.ui.RaceViewModel
import com.denisf.presentation.ui.navigation.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val startRaceUseCase = StartRaceUseCase()
        val raceViewModel = RaceViewModel(startRaceUseCase)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HorseRacingTheme {
                BottomNavigationBar(raceViewModel)
            }
        }
    }
}