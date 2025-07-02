package com.denisf.presentation.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.denisf.domain.model.Horse
import com.denisf.presentation.ui.RaceStatus
import com.denisf.presentation.ui.RaceViewModel

@Composable
fun RaceScreen(viewModel: RaceViewModel) {
    val race = viewModel.raceState.collectAsState().value
    val raceStatus = viewModel.raceStatus.value

    Column(modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)) {

        when (raceStatus) {
            RaceStatus.RACE -> {
                if (race?.isFinished == true) {
                    Text("🏆 Победитель: ${race.winnerId}")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    race?.horses?.forEach { horse ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HorseTrackVertical(horse = horse, maxProgress = race.trackLength, winner = race.winnerId == horse.id)
                        }
                    }
                }
            }

            RaceStatus.SETTING -> {
                Button(onClick = {
                    viewModel.startRace(6, 10)
                }) {
                    Text("Старт")
                }
            }
        }
    }
}

@Composable
fun HorseTrackVertical(
    horse: Horse,
    trackHeight: Dp = 400.dp,
    maxProgress: Int,
    winner: Boolean
) {
    val progressFraction = (horse.progress / maxProgress).coerceIn(0f, 1f)
    val animatedOffset by animateDpAsState(
        targetValue = (1f - progressFraction) * trackHeight
    )

    Box(
        modifier = Modifier
            .width(10.dp)
            .height(trackHeight)
            .background(if (winner) Color.Green else Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(4.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .offset(y = animatedOffset)
                .size(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("🐎", fontSize = 26.sp)
        }
    }

    Text(
        text = horse.name,
        fontSize = 12.sp
    )
}

//@Preview
//@Composable
//private fun RacePreview() {
//
//    RaceScreen()
//}