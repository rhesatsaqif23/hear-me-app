package com.example.hearme

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.hearme.cache.ListDoctor
import com.example.hearme.data.model.ArticleData
import com.example.hearme.data.repository.AuthRepositoryImpl
import com.example.hearme.domain.model.ArticleDomain
import com.example.hearme.domain.model.ConsultDomain
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.domain.usecase.AuthUseCase
import com.example.hearme.presentation.analysis.HistoryScreen
import com.example.hearme.presentation.analysis.QuestionScreen
import com.example.hearme.presentation.analysis.SummaryScreen
import com.example.hearme.presentation.analysis.VoiceRecordScreen
import com.example.hearme.presentation.auth.AuthViewModel
import com.example.hearme.presentation.auth.LoginScreen
import com.example.hearme.presentation.auth.RegisterScreen
import com.example.hearme.presentation.consultation.ConsultationScreen
import com.example.hearme.presentation.consultation.DoctorDetailScreen
import com.example.hearme.presentation.consultation.InboxScreen
import com.example.hearme.presentation.consultation.PaymentScreen
import com.example.hearme.presentation.consultation.ScheduleScreen
import com.example.hearme.presentation.consultation.SuccessScreen
import com.example.hearme.presentation.dashboard.ArticleScreen
import com.example.hearme.presentation.dashboard.DashboardScreen
import com.example.hearme.presentation.exercise.ExerciseDetailScreen
import com.example.hearme.presentation.exercise.ExerciseScreen
import com.example.hearme.presentation.navigation.BottomNavItem
import com.example.hearme.presentation.navigation.NavGraph
import com.example.hearme.presentation.profile.ChangePasswordScreen
import com.example.hearme.presentation.profile.EditProfileScreen
import com.example.hearme.presentation.profile.ProfileScreen
import com.example.hearme.presentation.splash.SplashScreen
import com.example.hearme.ui.theme.HearMeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            HearMeTheme {
                val navController = rememberNavController()

                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel =
                    viewModel(factory = AuthViewModel.Factory(authUseCase))

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    NavGraph(
                        navController = navController,
                        authViewModel = authViewModel,
                        authUseCase = authUseCase,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}