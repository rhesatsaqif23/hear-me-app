package com.example.hearme.presentation.analysis

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.model.RecommendationData
import com.example.hearme.data.model.StressData
import com.example.hearme.presentation.component.*
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.violetNormal

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun SummaryScreen(navController: NavController) {
    // Ambil ViewModel dari parent navGraph "analysis"
    val parentEntry = remember(navController) {
        navController.getBackStackEntry("analysis")
    }
    val viewModel: QuestionViewModel = viewModel(parentEntry)

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MainButton(
                    text = "Lihat Riwayat",
                    onClick = { navController.navigate("history") }
                )
                SecondButton(
                    text = "Konsultasikan",
                    onClick = { navController.navigate("consultation") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rangkuman Emosi Anda",
                style = Typography.titleLarge,
                color = violetNormal,
                textAlign = TextAlign.Center
            )

            when (val state = uiState) {
                is StressUiState.Idle -> {
                    Log.d("SummaryScreen", "State: Idle")
                    Text(
                        text = "Belum ada analisis",
                        style = Typography.bodyMedium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                is StressUiState.Loading -> {
                    Log.d("SummaryScreen", "State: Loading")
                    CircularProgressIndicator(
                        modifier = Modifier.padding(top = 32.dp),
                        color = violetNormal
                    )
                }

                is StressUiState.Success -> {
                    val result = state.result
                    Log.d(
                        "SummaryScreen",
                        "State: Success | level=${result.level}, recos=${result.recommendations.size}"
                    )

                    StressCard(
                        stress = StressData(level = result.level)
                    )

                    RecommendationCard(
                        recommendation = RecommendationData(
                            title = "Rekomendasi",
                            content = result.recommendations.joinToString("\n\n") { "• $it" }
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }

                is StressUiState.Error -> {
                    Log.d("SummaryScreen", "State: Error ${state.message}")
                    Text(
                        text = state.message,
                        color = Color.Red,
                        style = Typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryScreenPreview() {
    HearMeTheme {
        SummaryScreen(
            navController = rememberNavController()
        )
    }
}
