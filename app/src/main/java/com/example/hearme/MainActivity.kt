package com.example.hearme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.repository.AuthRepositoryImpl
import com.example.hearme.domain.model.ConsultDomain
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.domain.usecase.AuthUseCase
import com.example.hearme.presentation.analysis.QuestionScreen
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
import com.example.hearme.presentation.dashboard.DashboardScreen
import com.example.hearme.presentation.exercise.ExerciseScreen
import com.example.hearme.presentation.navigation.BottomNavItem
import com.example.hearme.presentation.profile.ChangePasswordScreen
import com.example.hearme.presentation.profile.EditProfileScreen
import com.example.hearme.presentation.profile.ProfileScreen
import com.example.hearme.ui.theme.HearMeTheme

class MainActivity : ComponentActivity() {
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
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
//                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Auth
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

                        // Bottom nav items
                        composable(BottomNavItem.Dashboard.route) {
                            DashboardScreen(navController = navController)
                        }
                        composable(BottomNavItem.Analysis.route) {
                            VoiceRecordScreen(navController = navController)
                        }
                        composable("question") {
                            QuestionScreen(navController = navController)
                        }
                        composable(BottomNavItem.Consultation.route) {
                            ConsultationScreen(navController = navController)
                        }
                        composable(BottomNavItem.Exercise.route) {
                            ExerciseScreen(navController = navController)
                        }
                        composable(BottomNavItem.Profile.route) {
                            ProfileScreen(navController = navController)
                        }

                        // Consultation
                        composable("doctorDetail") {
                            val dummyDoctor = DoctorDomain(
                                did = "1",
                                dName = "dr. Tirta, S.Psi",
                                specialist = "Psikiater, Kejiwaan",
                                rating = 4.5,
                                experience = 10,
                                clinic = "Klinik Sehat Raya, Jakarta",
                                address = "Jl. Bunga Dahlia No. 105",
                                city = "Jakarta Timur",
                                education = listOf(
                                    "S1 – Psikologi, Universitas Indonesia",
                                    "S1 – Kedokteran, Universitas Indonesia"
                                ),
                                dPhoneNumber = "08123456789",
                                photo = "https://i.imgur.com/mwL6QF5.jpeg"
                            )
                            DoctorDetailScreen(
                                navController = navController,
                                doctor = dummyDoctor
                            )
                        }
                        composable("inbox") {
                            InboxScreen(navController = navController)
                        }
                        composable("payment") {
                            PaymentScreen(navController = navController)
                        }
                        composable("schedule") {
                            val dummyDoctor = DoctorDomain(
                                did = "1",
                                dName = "dr. Tirta, S.Psi",
                                specialist = "Psikiater, Kejiwaan",
                                rating = 4.5,
                                experience = 10,
                                clinic = "Klinik Sehat Raya, Jakarta",
                                address = "Jl. Bunga Dahlia No. 105",
                                city = "Jakarta Timur",
                                education = listOf(
                                    "S1 – Psikologi, Universitas Indonesia",
                                    "S1 – Kedokteran, Universitas Indonesia"
                                ),
                                dPhoneNumber = "08123456789",
                                photo = "https://i.imgur.com/mwL6QF5.jpeg"
                            )
                            ScheduleScreen(navController = navController, doctor = dummyDoctor)
                        }
                        composable("success") {
                            val dummyDoctor = DoctorDomain(
                                did = "1",
                                dName = "dr. Tirta, S.Psi",
                                specialist = "Psikiater, Kejiwaan",
                                rating = 4.5,
                                experience = 10,
                                clinic = "Klinik Sehat Raya, Jakarta",
                                address = "Jl. Bunga Dahlia No. 105",
                                city = "Jakarta Timur",
                                education = listOf(
                                    "S1 – Psikologi, Universitas Indonesia",
                                    "S1 – Kedokteran, Universitas Indonesia"
                                ),
                                dPhoneNumber = "08123456789",
                                photo = "https://i.imgur.com/mwL6QF5.jpeg"
                            )
                            val dummyConsult = ConsultDomain(
                                cid = "C001",
                                did = "1",
                                uid = "U001",
                                date = "Jumat, 5 September 2025",
                                time = "11.00 WIB",
                                payment = "QRIS",
                                clinic = "Klinik Sehat Raya, Jakarta Timur"
                            )
                            SuccessScreen(
                                navController = navController,
                                doctor = dummyDoctor,
                                consult = dummyConsult
                            )
                        }

                        // Profile
                        composable("changePassword") {
                            ChangePasswordScreen(navController = navController)
                        }
                        composable("editProfile") {
                            EditProfileScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

