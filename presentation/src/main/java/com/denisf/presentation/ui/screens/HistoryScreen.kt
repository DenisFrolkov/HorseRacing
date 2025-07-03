package com.denisf.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisf.domain.model.FinishedRace
import com.denisf.presentation.ui.theme.PaleOrangeYellow
import com.denisf.presentation.ui.theme.OrangeYellow
import com.denisf.presentation.ui.theme.PaleGrayBrown

@Composable
fun HistoryScreen() {
    val winners = listOf<FinishedRace>(
        FinishedRace(0, 1, "02.07.2025", 1, 2),
        FinishedRace(1, 2, "02.07.2025", 4, 6),
        FinishedRace(2, 3, "04.07.2025", 3, 2),
        FinishedRace(3, 4, "02.07.2025", 2, 6),
        FinishedRace(4, 5, "12.07.2025", 6, 4),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleOrangeYellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "История забегов",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PaleGrayBrown.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .border(1.dp,OrangeYellow, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("№ ${finishedRace.numberRace}")
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(
                "Победила лошадь №${finishedRace.winner}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("Участников - ${finishedRace.horsesCount}")
                Text("Дата - ${finishedRace.date}")
            }
        }
    }
}

@Preview
@Composable
private fun HistoryPreview() {
    HistoryScreen()
}