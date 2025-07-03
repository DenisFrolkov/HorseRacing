package com.denisf.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.denisf.presentation.ui.screens.HistoryScreen
import com.denisf.presentation.ui.screens.RaceScreen
import com.denisf.presentation.ui.theme.PaleGrayBrown
import com.denisf.presentation.ui.viewModel.HistoryViewModel
import com.denisf.presentation.ui.viewModel.RaceViewModel

@Composable
fun BottomNavigationBar(raceViewModel: RaceViewModel, historyViewModel: HistoryViewModel) {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Race, BottomNavItem.History)

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.title,
                                tint = Color.Black,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        label = { Text(item.title, color = Color.Black, fontSize = 14.sp) },
                        selected = currentRoute == item.route,
                        modifier = Modifier.background(
                            color = if (currentRoute == item.route) PaleGrayBrown else PaleGrayBrown.copy(
                                0.5f
                            )
                        ),
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Race.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Race.route) { RaceScreen(raceViewModel) }
            composable(BottomNavItem.History.route) { HistoryScreen(historyViewModel) }
        }
    }
}

//@Preview(
//    device = "spec:parent=pixel_7",
//)
//@Composable
//private fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
//}