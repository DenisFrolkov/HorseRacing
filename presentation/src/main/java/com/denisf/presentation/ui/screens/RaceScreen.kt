package com.denisf.presentation.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.denisf.domain.model.Horse
import com.denisf.presentation.ui.RaceStatus
import com.denisf.presentation.ui.RaceViewModel
import com.denisf.presentation.ui.theme.BrownYellowWiltedLeaves
import com.denisf.presentation.ui.theme.PaleGrayBrown
import com.denisf.presentation.ui.theme.PaleOrangeYellow
import com.denisf.presentation.ui.theme.ShinyYellow

@Composable
fun RaceScreen(viewModel: RaceViewModel) {
    val race = viewModel.raceState.collectAsState().value
    val raceStatus = viewModel.raceStatus.value

    val winHorse = (race?.winnerId ?: 0) + 1

    val horseCountText by viewModel.horseCountText.collectAsState()
    val isValidHorseCount by viewModel.isValidHorseCount.collectAsState()

    val raceLengthText by viewModel.raceLengthText.collectAsState()
    val isValidRaceLength by viewModel.isValidRaceLength.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleOrangeYellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (raceStatus != RaceStatus.START) {
                Spacer(modifier = Modifier.height(20.dp))
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
                        .padding(8.dp)
                        .background(BrownYellowWiltedLeaves, RoundedCornerShape(10.dp)),
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
                Column(
                    modifier = Modifier
                        .fillMaxHeight(.75f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    TextField(
                        value = horseCountText,
                        onValueChange = { viewModel.updateHorseCountText(it) },
                        label = { Text(text = "Введите количество лошадей (2-6)") },
                        singleLine = true,
                        isError = horseCountText.isNotBlank() && !isValidHorseCount,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            unfocusedContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            errorContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            focusedLabelColor = Color.DarkGray,
                            unfocusedLabelColor = Color.DarkGray,
                            errorLabelColor = Color.Red,
                            focusedIndicatorColor = Color.DarkGray,
                            unfocusedIndicatorColor = Color.LightGray,
                            errorIndicatorColor = Color.Red,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
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
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            unfocusedContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            errorContainerColor = PaleGrayBrown.copy(alpha = 0.5f),
                            focusedLabelColor = Color.DarkGray,
                            unfocusedLabelColor = Color.DarkGray,
                            errorLabelColor = Color.Red,
                            focusedIndicatorColor = Color.DarkGray,
                            unfocusedIndicatorColor = Color.LightGray,
                            errorIndicatorColor = Color.Red,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                    )
                }
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
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaleGrayBrown,
                        contentColor = Color.White
                    )
                ) {
                    Text("Старт")
                }
            }

            RaceStatus.FINISHED -> {
                Button(
                    onClick = {
                        viewModel.updateRaceStatus(RaceStatus.START)
                        viewModel.clearInput()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaleGrayBrown,
                        contentColor = Color.White
                    )
                ) {
                    Text("Заново")
                }
            }

            RaceStatus.RUNNING -> {
                Button(
                    onClick = {
                        viewModel.stopRace()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaleGrayBrown,
                        contentColor = Color.White
                    )
                ) {
                    Text("Завершить забег")
                }
            }
        }
    }
}

@Composable
fun HorseTrackVertical(
    horse: Horse,
    trackHeight: Dp = 375.dp,
    maxProgress: Int,
    winner: Boolean
) {
    val rawProgressFraction = (horse.progress / maxProgress).coerceIn(0f, 1f)

    val animatedProgressFraction by animateFloatAsState(
        targetValue = rawProgressFraction,
        animationSpec = tween(durationMillis = 120),
    )

    val animatedHorseFraction by animateDpAsState(
        targetValue = (1f - animatedProgressFraction) * (trackHeight),
        animationSpec = tween(durationMillis = 0),
    )
    val adjustedOffset = animatedHorseFraction - 50.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(60.dp)
    ) {
        Text(
            text = horse.name,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(trackHeight)
        ) {
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(trackHeight)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background( Color.LightGray)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(animatedProgressFraction)
                        .align(Alignment.BottomCenter)
                        .background(if (winner) ShinyYellow else Color.Gray)
                )
            }

            Column(
                modifier = Modifier
                    .offset(y = adjustedOffset)
                    .align(Alignment.TopCenter)
                    .width(60.dp)
                    .height(100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🐎", fontSize = 30.sp)
            }
        }
    }
}

//@Preview
//@Composable
//private fun RacePreview() {
//
//    RaceScreen()
//}