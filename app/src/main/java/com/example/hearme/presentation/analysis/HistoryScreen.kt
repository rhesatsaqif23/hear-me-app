package com.example.hearme.presentation.analysis

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.model.HistoryData
import com.example.hearme.presentation.component.HistoryCard
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.HearMeTheme

@Composable
fun HistoryScreen(
    navController: NavController,
    onBackClick: () -> Unit = { navController.popBackStack() }
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Riwayat",
                onBackClick = onBackClick,
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HistoryCard(
                history = HistoryData(
                    level = "Sedang",
                    date = "Kam, 05 Sep 2025"
                )
            )
            HistoryCard(
                history = HistoryData(
                    level = "Ringan",
                    date = "Sen, 01 Sep 2025"
                )
            )
            HistoryCard(
                history = HistoryData(
                    level = "Berat",
                    date = "Sab, 23 Agu 2025"
                )
            )
            HistoryCard(
                history = HistoryData(
                    level = "Sedang",
                    date = "Sel, 19 Agu 2025"
                )
            )
            HistoryCard(
                history = HistoryData(
                    level = "Ringan",
                    date = "Sen, 11 Agu 2025"
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HearMeTheme {
        HistoryScreen(navController = rememberNavController())
    }
}
