package com.example.hearme.presentation.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.presentation.component.ExerciseStepCard
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.Typography

@Composable
fun ExerciseDetailScreen(
    navController: NavController,
    onBackClick: () -> Unit = { navController.popBackStack() },
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.header_olahraga),
                    contentDescription = "Header Exercise",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                TopBar(
                    title = "",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Peregangan Singkat",
                    style = Typography.titleLarge,
                    color = Color(0xFFFF698C)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ExerciseStepCard(
                        title = "Grounding & napas (1 menit)",
                        content = "Duduk tegak, tarik napas 4 hitungan, tahan 2, hembuskan 6. Ulangi beberapa kali."
                    )
                    ExerciseStepCard(
                        title = "Leher Samping (20–30 dtk per sisi)",
                        content = "Miringkan kepala ke kanan, tahan, lalu ganti kiri. Bahu tetap rileks."
                    )
                    ExerciseStepCard(
                        title = "Leher Diagonal (20–30 dtk per sisi)",
                        content = "Arahkan dagu ke ketiak kanan, tahan. Ulangi sisi kiri."
                    )
                    ExerciseStepCard(
                        title = "Putaran Bahu & Dada (1 menit)",
                        content = "Putar bahu ke belakang 10 kali. Kaitkan tangan di belakang, tarik dada terbuka."
                    )
                    ExerciseStepCard(
                        title = "Cat–Cow Duduk (6–8 kali)",
                        content = "Tarik napas: busungkan dada. Hembuskan: punggung membulat."
                    )
                    ExerciseStepCard(
                        title = "Twist Torso (20–30 dtk per sisi)",
                        content = "Pegang sandaran kursi, putar perlahan tubuh ke kanan, lalu kiri."
                    )
                    ExerciseStepCard(
                        title = "Pinggul Figure–4 (20–30 dtk per sisi)",
                        content = "Silangkan pergelangan kaki di atas lutut lawan, condongkan badan sedikit ke depan."
                    )
                    ExerciseStepCard(
                        title = "Forward Fold (30–45 dtk)",
                        content = "Condongkan tubuh ke depan, biarkan kepala dan tangan rileks. Bangkit perlahan."
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseScreen() {
    val navController = rememberNavController()
    ExerciseDetailScreen(navController = navController)
}
