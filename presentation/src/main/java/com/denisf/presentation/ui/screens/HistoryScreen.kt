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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisf.domain.model.RaceHistory
import com.denisf.presentation.R
import com.denisf.presentation.ui.theme.OrangeYellow
import com.denisf.presentation.ui.theme.PaleGrayBrown
import com.denisf.presentation.ui.theme.PaleOrangeYellow
import com.denisf.presentation.ui.viewModel.HistoryViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel) {
    val history = historyViewModel.raceHistory.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
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
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            val reversedHistory = history.reversed()
            itemsIndexed(reversedHistory) { index, item ->
                val number = history.size - index
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    HistoryRaceItem(number, item)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.BottomEnd) {
            IconButton(
                onClick = {
                    historyViewModel.clearRaceHistory()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = PaleGrayBrown,
                    contentColor = Color.White
                ),
                modifier = Modifier.size(50.dp)
            ) {
                Icon(painter = painterResource(R.drawable.clear_icon), tint = Color.Black, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Composable
private fun HistoryRaceItem(idItem: Int, raceHistory: RaceHistory) {
    val winNumber = (raceHistory.winner ?: 0) + 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PaleGrayBrown.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .border(1.dp, OrangeYellow, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("№ $idItem", color = Color.Black,)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Победила лошадь №${winNumber}",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Участников - ${raceHistory.horsesCount}", color = Color.Black)
                Text("Дата - ${raceHistory.date}", color = Color.Black)
            }
        }
    }
}

//@Preview
//@Composable
//private fun HistoryPreview() {
//    HistoryScreen()
//}