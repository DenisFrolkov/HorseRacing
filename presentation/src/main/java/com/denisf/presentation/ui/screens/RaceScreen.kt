package com.denisf.presentation.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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

    val winHorse = (race?.winnerId ?: 0) + 1

    val horseCountText by viewModel.horseCountText.collectAsState()
    val isValidHorseCount by viewModel.isValidHorseCount.collectAsState()

    val raceLengthText by viewModel.raceLengthText.collectAsState()
    val isValidRaceLength by viewModel.isValidRaceLength.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (raceStatus != RaceStatus.START) {
                when {
                    race?.isFinished == true -> {
                        Text(
                            text = "🏁 Победила лошадь под номером $winHorse!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    race?.isRunning == true -> {
                        Text(
                            text = "Забег начался!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
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
                            HorseTrackVertical(
                                horse = horse,
                                maxProgress = race.trackLength,
                                winner = race.winnerId == horse.id
                            )
                        }
                    }
                }
            } else {
                TextField(
                    value = horseCountText,
                    onValueChange = { viewModel.updateHorseCountText(it) },
                    label = { Text(text = "Введите количество лошадей (2-6)") },
                    singleLine = true,
                    isError = horseCountText.isNotBlank() && !isValidHorseCount,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = raceLengthText,
                    onValueChange = {
                        viewModel.updaterRaceLengthText(it)
                    },
                    label = { Text(text = "Введите длину забега") },
                    singleLine = true,
                    isError = raceLengthText.isNotBlank() && !isValidRaceLength,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        when (raceStatus) {
            RaceStatus.START -> {
                Button(
                    onClick = {
                        if (isValidHorseCount && isValidRaceLength) {
                            viewModel.startRace(
                                horseCount = viewModel.horseCount.value,
                                trackLength = viewModel.raceLength.value
                            )
                        }
                    }
                ) {
                    Text("Старт")
                }
            }

            RaceStatus.FINISHED -> {
                Button(onClick = {
                    viewModel.updateRaceStatus(RaceStatus.START)
                    viewModel.clearInput()
                }) {
                    Text("Заново")
                }
            }

            RaceStatus.RUNNING -> {
                Button(onClick = {
                    viewModel.stopRace()
                }) {
                    Text("Завершить забег")
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
            .background(
                if (winner) Color.Green else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
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