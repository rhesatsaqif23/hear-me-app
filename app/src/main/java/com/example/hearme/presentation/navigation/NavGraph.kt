package com.example.hearme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hearme.cache.ListDoctor
import com.example.hearme.domain.model.ArticleDomain
import com.example.hearme.domain.model.ConsultDomain
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
import com.example.hearme.presentation.profile.ChangePasswordScreen
import com.example.hearme.presentation.profile.EditProfileScreen
import com.example.hearme.presentation.profile.ProfileScreen
import com.example.hearme.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    authUseCase: AuthUseCase,
    modifier: Modifier = Modifier
) {
    val doctors = ListDoctor.doctors

    NavHost(
        navController = navController,
//        startDestination = Routes.Dashboard.route,
        startDestination = Routes.Splash.route,
        modifier = modifier
    ) {
        // Splash
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Auth
        composable(Routes.Login.route) {
            LoginScreen(viewModel = authViewModel, navController = navController)
        }
        composable(Routes.Register.route) {
            RegisterScreen(viewModel = authViewModel, navController = navController)
        }

        // Bottom nav items
        composable(Routes.Dashboard.route) {
            DashboardScreen(navController = navController, authUseCase = authUseCase)
        }
        composable(Routes.Consultation.route) {
            ConsultationScreen(doctors = doctors, navController = navController)
        }
        composable(Routes.Exercise.route) {
            ExerciseScreen(navController = navController)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController = navController, authUseCase = authUseCase)
        }

        // Analysis (nested graph)
        navigation(
            startDestination = Routes.VoiceRecord.route,
            route = Routes.Analysis.route
        ) {
            composable(Routes.VoiceRecord.route) {
                VoiceRecordScreen(navController = navController)
            }
            composable(Routes.Question.route) {
                QuestionScreen(navController = navController)
            }
            composable(Routes.Summary.route) {
                SummaryScreen(navController = navController)
            }
            composable(Routes.History.route) {
                HistoryScreen(navController = navController)
            }
        }

        // Article
        composable(Routes.Article.route) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val author = backStackEntry.arguments?.getString("author") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val content = backStackEntry.arguments?.getString("content") ?: ""
            val photo = backStackEntry.arguments?.getString("photo")?.toIntOrNull() ?: 0

            val article = ArticleDomain(title, author, date, content, photo)

            ArticleScreen(navController = navController, article = article)
        }

        // Inbox
        composable(Routes.Inbox.route) {
            InboxScreen(navController = navController)
        }

        // Doctor detail
        composable(
            Routes.DoctorDetail.route,
            arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")
            val doctor = doctors.find { it.did == doctorId }
            doctor?.let {
                DoctorDetailScreen(navController = navController, doctor = it)
            }
        }

        // Schedule
        composable(
            Routes.Schedule.route,
            arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")
            val doctor = doctors.find { it.did == doctorId }
            doctor?.let {
                ScheduleScreen(navController = navController, doctor = it)
            }
        }

        // Payment
        composable(Routes.Payment.route,
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
            doctor?.let {
                PaymentScreen(navController = navController, doctor = it, consult = consult)
            }
        }

        // Success
        composable(Routes.Success.route,
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
            doctor?.let {
                SuccessScreen(navController = navController, doctor = it, consult = consult)
            }
        }

        // Exercise
        composable(Routes.ExerciseDetail.route) {
            ExerciseDetailScreen(navController = navController)
        }

        // Profile
        composable(Routes.ChangePassword.route) {
            ChangePasswordScreen(navController = navController)
        }
        composable(Routes.EditProfile.route) {
            EditProfileScreen(navController = navController)
        }
    }
}
