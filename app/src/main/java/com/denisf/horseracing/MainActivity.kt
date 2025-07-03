package com.denisf.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.denisf.horseracing.app.App
import com.denisf.horseracing.ui.theme.HorseRacingTheme
import com.denisf.presentation.ui.navigation.BottomNavigationBar
import com.denisf.presentation.ui.viewModel.HistoryViewModel
import com.denisf.presentation.di.HistoryViewModelFactory
import com.denisf.presentation.ui.viewModel.RaceViewModel
import com.denisf.presentation.di.RaceViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var raceViewModelFactory: RaceViewModelFactory
    private lateinit var raceViewModel: RaceViewModel

    @Inject
    lateinit var historyViewModelFactory: HistoryViewModelFactory
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        raceViewModel = ViewModelProvider(this, raceViewModelFactory).get(RaceViewModel::class.java)
        historyViewModel = ViewModelProvider(this, historyViewModelFactory).get(HistoryViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            HorseRacingTheme {
                BottomNavigationBar(raceViewModel, historyViewModel)
            }
        }
    }
}