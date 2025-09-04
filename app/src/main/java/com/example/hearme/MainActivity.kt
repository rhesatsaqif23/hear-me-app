package com.example.hearme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.repository.AuthRepositoryImpl
import com.example.hearme.domain.usecase.AuthUseCase
import com.example.hearme.presentation.auth.AuthViewModel
import com.example.hearme.presentation.auth.LoginScreen
import com.example.hearme.presentation.auth.RegisterScreen
import com.example.hearme.presentation.dashboard.DashboardScreen
import com.example.hearme.ui.theme.HearMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HearMeTheme {
                val navController = rememberNavController()

                // Inisiasi AuthViewModel pakai factory
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel =
                    viewModel(factory = AuthViewModel.Factory(authUseCase))

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "register",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            LoginScreen(
                                viewModel = authViewModel,
                                navController = navController
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                viewModel = authViewModel,
                                navController = navController
                            )
                        }
                        composable("home") {
                            DashboardScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
