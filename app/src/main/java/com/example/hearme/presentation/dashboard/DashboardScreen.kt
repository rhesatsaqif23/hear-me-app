package com.example.hearme.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentRoute) {
                "home" -> {
                    Text(
                        "Selamat datang di Dashboard 👋",
                        style = Typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Di sini nanti bisa ditambahkan menu navigasi atau ringkasan data.")
                }
                "dokter" -> {
                    Text("Halaman Pesan", style = Typography.headlineSmall)
                }
                "profil" -> {
                    Text("Halaman Profil", style = Typography.headlineSmall)
                }
            }
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
