package com.example.hearme.presentation.analysis

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.cache.ListQuestion
import com.example.hearme.data.model.UserAnswer
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.QuestionSection
import com.example.hearme.presentation.component.SwitchMethod
import com.example.hearme.presentation.navigation.BottomNavBar

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun QuestionScreen(
    navController: NavController,
    currentRoute: String = "voiceRecord"
) {
    val questions = ListQuestion.questions
    val userAnswers = remember { mutableStateListOf<UserAnswer>() }

    // Ambil ViewModel dari parent navGraph "analysis"
    val parentEntry = remember(navController) {
        navController.getBackStackEntry("analysis")
    }
    val viewModel: QuestionViewModel = viewModel(parentEntry)

    val uiState by viewModel.uiState.collectAsState()

    val totalQuestions = questions.sumOf { it.questions.size }
    val allAnswered = userAnswers.size == totalQuestions
    val context = LocalContext.current

    // Observe perubahan uiState → kalau Success/Error, navigate
    LaunchedEffect(uiState) {
        when (uiState) {
            is StressUiState.Success -> {
                Log.d("QuestionScreen", "Analisis selesai, hasil siap")
                navController.navigate("summary") {
                    popUpTo("analysis") { inclusive = false }
                    launchSingleTop = true
                }
            }

            is StressUiState.Error -> {
                val message = (uiState as StressUiState.Error).message
                Log.e("QuestionScreen", "Analisis gagal: $message")
                Toast.makeText(context, "Analisis gagal: $message", Toast.LENGTH_SHORT).show()
            }

            is StressUiState.Loading -> {
                Log.d("QuestionScreen", "Sedang menganalisis jawaban...")
            }

            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SwitchMethod(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                defaultSelected = true,
                onMethodSelected = { method ->
                    if (method == "voiceRecord") {
                        navController.navigate("voiceRecord") {
                            popUpTo("analysis") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                questions.forEach { section ->
                    QuestionSection(
                        title = section.title,
                        questions = section.questions,
                        onAnswer = { question, answer ->
                            userAnswers.removeAll { it.question == question }
                            userAnswers.add(
                                UserAnswer(
                                    section = section.title,
                                    question = question,
                                    answer = answer
                                )
                            )
                        }
                    )
                }
            }

            MainButton(
                text = "Simpan",
                onClick = {
                    if (allAnswered) {
                        Log.d("QuestionScreen", "Kirim jawaban ke Gemini...")
                        viewModel.analyzeAnswers(userAnswers)
                    } else {
                        Toast.makeText(
                            context,
                            "Harap isi semua pertanyaan terlebih dahulu",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    val navController = rememberNavController()
    QuestionScreen(navController)
}
