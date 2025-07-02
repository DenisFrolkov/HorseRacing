package com.denisf.presentation.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.denisf.presentation.ui.screens.HistoryScreen
import com.denisf.presentation.ui.screens.RaceScreen

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Race, BottomNavItem.History)

    Scaffold(
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        label = { Text(item.title, fontSize = 14.sp) },
                        selected = currentRoute == item.route,
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
            composable(BottomNavItem.Race.route) { RaceScreen() }
            composable(BottomNavItem.History.route) { HistoryScreen() }
        }
    }
}

@Preview(
    device = "spec:parent=pixel_7",
)
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}