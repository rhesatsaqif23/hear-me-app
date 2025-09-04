package com.example.hearme.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hearme.presentation.component.TopBarHome
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.HearMeTheme

@Composable
fun DashboardScreen(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = "dashboard"

    Scaffold(
        topBar = {
            TopBarHome(
                name = "Shevia Neyliana",
                notificationCount = 0
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HearMeTheme {
        val navController = rememberNavController()
        DashboardScreen(navController = navController)
    }
}
