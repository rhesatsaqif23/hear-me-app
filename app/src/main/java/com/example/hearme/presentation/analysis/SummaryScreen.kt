package com.example.hearme.presentation.analysis

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.model.RecommendationData
import com.example.hearme.data.model.StressData
import com.example.hearme.presentation.component.*
import com.example.hearme.presentation.consultation.ConsultationScreen
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.violetNormal

@Composable
fun SummaryScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = androidx.compose.ui.graphics.Color.White,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MainButton(
                    text = "Lihat Riwayat",
                    onClick = {
                        navController.navigate("history")
                    }
                )
                SecondButton(
                    text = "Konsultasikan",
                    onClick = {
                        navController.navigate("consultation")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "Rangkuman Emosi Anda",
                style = Typography.titleLarge,
                color = violetNormal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(1.dp))

            DetectCard()

            StressCard(
                stress = StressData(level = "sedang")
            )

            RecommendationCard(
                recommendation = RecommendationData(
                    title = "Langkah Awal",
                    content = "Cobalah tarik napas dalam-dalam lalu hembuskan perlahan untuk menenangkan diri. Jangan memendam perasaan sendirian, ceritakan pada teman atau orang terdekat agar beban terasa lebih ringan. Istirahat yang cukup juga penting, karena kurang tidur hanya akan memperburuk kondisi."
                )
            )
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
