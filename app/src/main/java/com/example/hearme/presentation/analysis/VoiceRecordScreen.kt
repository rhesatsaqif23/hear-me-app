package com.example.hearme.presentation.analysis

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.RecorderState
import com.example.hearme.presentation.component.SwitchMethod
import com.example.hearme.presentation.component.VoiceRecorder
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.Typography

@Composable
fun VoiceRecordScreen(
    navController: NavController,
    currentRoute: String = "voiceRecord"
) {
    var recorderState by remember { mutableStateOf(RecorderState.Idle) }

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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SwitchMethod(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                defaultSelected = false,
                onMethodSelected = { method ->
                    when (method) {
                        "question" -> {
                            navController.navigate("question") {
                                popUpTo("voiceRecord") { inclusive = false }
                                launchSingleTop = true
                            }
                        }

                        "voiceRecord" -> {
                            navController.navigate("voiceRecord") {
                                popUpTo("question") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                VoiceRecorder(
                    state = recorderState,
                    onStart = { recorderState = RecorderState.Recording },
                    onStop = { recorderState = RecorderState.Idle }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Tekan untuk merekam suara Anda",
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }

            MainButton(
                text = "Simpan",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoiceRecordScreenPreview() {
    val navController = rememberNavController()
    VoiceRecordScreen(navController)
}
