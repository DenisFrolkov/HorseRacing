package com.denisf.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisf.domain.model.FinishedRace

@Composable
fun HistoryScreen() {
    val winners = listOf<FinishedRace>(
        FinishedRace(0, 1,"02.07.2025", 1, 2),
        FinishedRace(1, 2,"02.07.2025", 4, 6),
        FinishedRace(2, 3,"04.07.2025", 3, 2),
        FinishedRace(3, 4,"02.07.2025", 2, 6),
        FinishedRace(4, 5,"12.07.2025", 6, 4),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "История забегов",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        items(winners) {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                HistoryRaceItem(it)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HistoryRaceItem(finishedRace: FinishedRace) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.LightGray), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        Text("№ ${finishedRace.numberRace}")
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text("Победила лошадь №${finishedRace.winner}")
            Row {
                Text("Забег прошел - ${finishedRace.date}")
                Text("Всего участников - ${finishedRace.horsesCount}")
            }
        }
    }
}


@Preview
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}