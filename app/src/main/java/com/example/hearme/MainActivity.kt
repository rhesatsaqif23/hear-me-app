package com.example.hearme

import android.annotation.SuppressLint
import android.os.Bundle
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

                val doctors = listOf(
                    DoctorDomain(
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
                            "S1 – Kedokteran, Universitas Indonesia",
                            "Sp.KJ – Spesialis Kedokteran Jiwa, Universitas Gadjah Mada"
                        ),
                        dPhoneNumber = "08123456789",
                        photo = R.drawable.doctor_1
                    ),
                    DoctorDomain(
                        did = "2",
                        dName = "dr. Sinta, Sp.KJ",
                        specialist = "Psikiater",
                        rating = 4.8,
                        experience = 7,
                        clinic = "Mental Health Center",
                        address = "Jl. Merdeka No. 8",
                        city = "Jakarta Barat",
                        education = listOf(
                            "S1 – Psikologi, Universitas Airlangga",
                            "S1 – Kedokteran, Universitas Indonesia",
                            "Sp.KJ – Spesialis Kedokteran Jiwa, Universitas Indonesia"
                        ),
                        dPhoneNumber = "08234567890",
                        photo = R.drawable.doctor_2
                    ),
                    DoctorDomain(
                        did = "3",
                        dName = "dr. Maya, S.Psi",
                        specialist = "Psikolog Anak",
                        rating = 4.7,
                        experience = 6,
                        clinic = "Happy Mind Center",
                        address = "Jl. Diponegoro No. 10",
                        city = "Jakarta Selatan",
                        education = listOf(
                            "S1 – Psikologi, Universitas Diponegoro",
                            "M.Psi – Magister Psikologi, Universitas Indonesia",
                            "Konsentrasi – Psikologi Perkembangan Anak"
                        ),
                        dPhoneNumber = "08456789012",
                        photo = R.drawable.doctor_3
                    ),
                    DoctorDomain(
                        did = "4",
                        dName = "dr. Andi, S.Psi",
                        specialist = "Psikolog Klinis",
                        rating = 4.6,
                        experience = 4,
                        clinic = "MindCare Clinic",
                        address = "Jl. Sudirman No. 5",
                        city = "Tangerang",
                        education = listOf(
                            "S1 – Psikologi, Universitas Gadjah Mada",
                            "M.Psi – Magister Psikologi Klinis, Universitas Gadjah Mada"
                        ),
                        dPhoneNumber = "08345678901",
                        photo = R.drawable.doctor_4
                    )
                )


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
//                        startDestination = "splash",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Splash
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }

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
                            ConsultationScreen(doctors = doctors, navController = navController)
                        }
                        composable(BottomNavItem.Exercise.route) {
                            ExerciseScreen(navController = navController)
                        }
                        composable(BottomNavItem.Profile.route) {
                            ProfileScreen(navController = navController)
                        }

                        // Article
                        composable(
                            route = "article/{title}/{author}/{date}/{content}/{photo}"
                        ) { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val author = backStackEntry.arguments?.getString("author") ?: ""
                            val date = backStackEntry.arguments?.getString("date") ?: ""
                            val content = backStackEntry.arguments?.getString("content") ?: ""
                            val photo =
                                backStackEntry.arguments?.getString("photo")?.toIntOrNull() ?: 0

                            val article = ArticleDomain(
                                title = title,
                                author = author,
                                date = date,
                                content = content,
                                photo = photo
                            )

                            ArticleScreen(
                                navController = navController,
                                article = article
                            )
                        }


                        // Analysis
                        composable("summary") {
                            SummaryScreen(navController = navController)
                        }
                        composable("history") {
                            HistoryScreen(navController = navController)
                        }

                        // Inbox
                        composable("inbox") {
                            InboxScreen(navController = navController)
                        }

                        // Doctor detail
                        composable(
                            route = "doctorDetail/{doctorId}",
                            arguments = listOf(navArgument("doctorId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val doctorId = backStackEntry.arguments?.getString("doctorId")
                            val doctor = doctors.find { it.did == doctorId }

                            doctor?.let {
                                DoctorDetailScreen(
                                    navController = navController,
                                    doctor = it
                                )
                            }
                        }

                        // Schedule
                        composable(
                            route = "schedule/{doctorId}",
                            arguments = listOf(navArgument("doctorId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val doctorId = backStackEntry.arguments?.getString("doctorId")
                            val doctor = doctors.find { it.did == doctorId }

                            doctor?.let {
                                ScheduleScreen(
                                    navController = navController,
                                    doctor = it
                                )
                            }
                        }

                        // Payment
                        composable(
                            route = "payment/{doctorId}/{cid}/{date}/{time}/{payment}",
                            arguments = listOf(
                                navArgument("doctorId") { type = NavType.StringType },
                                navArgument("cid") { type = NavType.StringType },
                                navArgument("date") { type = NavType.StringType },
                                navArgument("time") { type = NavType.StringType },
                                navArgument("payment") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val doctorId = backStackEntry.arguments?.getString("doctorId")
                            val doctor = doctors.find { it.did == doctorId }

                            val consult = ConsultDomain(
                                cid = backStackEntry.arguments?.getString("cid") ?: "",
                                did = doctorId ?: "",
                                dName = doctor?.dName ?: "",
                                clinic = doctor?.clinic ?: "",
                                date = backStackEntry.arguments?.getString("date") ?: "",
                                time = backStackEntry.arguments?.getString("time") ?: "",
                                payment = backStackEntry.arguments?.getString("payment") ?: ""
                            )

                            if (doctor != null) {
                                PaymentScreen(
                                    navController = navController,
                                    doctor = doctor,
                                    consult = consult
                                )
                            }
                        }

                        // Success
                        composable(
                            route = "success/{doctorId}/{cid}/{date}/{time}/{payment}",
                            arguments = listOf(
                                navArgument("doctorId") { type = NavType.StringType },
                                navArgument("cid") { type = NavType.StringType },
                                navArgument("date") { type = NavType.StringType },
                                navArgument("time") { type = NavType.StringType },
                                navArgument("payment") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val doctorId = backStackEntry.arguments?.getString("doctorId")
                            val doctor = doctors.find { it.did == doctorId }

                            val consult = ConsultDomain(
                                cid = backStackEntry.arguments?.getString("cid") ?: "",
                                did = doctorId ?: "",
                                dName = doctor?.dName ?: "",
                                clinic = doctor?.clinic ?: "",
                                date = backStackEntry.arguments?.getString("date") ?: "",
                                time = backStackEntry.arguments?.getString("time") ?: "",
                                payment = backStackEntry.arguments?.getString("payment") ?: ""
                            )

                            if (doctor != null) {
                                SuccessScreen(
                                    navController = navController,
                                    doctor = doctor,
                                    consult = consult
                                )
                            }
                        }

                        // Exercise
                        composable("exerciseDetail") {
                            ExerciseDetailScreen(navController = navController)
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