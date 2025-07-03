package com.denisf.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.denisf.horseracing.RaceAppDependencies.provideHistoryViewModel
import com.denisf.horseracing.RaceAppDependencies.provideRaceViewModel
import com.denisf.horseracing.ui.theme.HorseRacingTheme
import com.denisf.presentation.ui.navigation.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val raceViewModel = provideRaceViewModel(applicationContext)
            val historyViewModel = provideHistoryViewModel(applicationContext)

            HorseRacingTheme {
                BottomNavigationBar(raceViewModel, historyViewModel)
            }
        }
    }
}