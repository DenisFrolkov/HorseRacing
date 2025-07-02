package com.denisf.presentation.ui.navigation

import androidx.compose.ui.graphics.painter.Painter
import com.denisf.presentation.R

sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    object Race : BottomNavItem("race", R.drawable.icon_finish, "Скачки")
    object History : BottomNavItem("history", R.drawable.icon_history, "История")
}
