package com.example.hearme.presentation.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object Register : Routes("register")

    // Bottom nav
    object Dashboard : Routes("dashboard")
    object Consultation : Routes("consultation")
    object Exercise : Routes("exercise")
    object Profile : Routes("profile")

    // Analysis nested graph
    object Analysis : Routes("analysis")
    object VoiceRecord : Routes("voiceRecord")
    object Question : Routes("question")
    object Summary : Routes("summary")
    object History : Routes("history")

    // Article
    object Article : Routes("article/{title}/{author}/{date}/{content}/{photo}")

    // Inbox
    object Inbox : Routes("inbox")

    // Doctor
    object DoctorDetail : Routes("doctorDetail/{doctorId}")
    object Schedule : Routes("schedule/{doctorId}")

    // Payment & Success
    object Payment : Routes("payment/{doctorId}/{cid}/{date}/{time}/{payment}")
    object Success : Routes("success/{doctorId}/{cid}/{date}/{time}/{payment}")

    // Exercise detail
    object ExerciseDetail : Routes("exerciseDetail")

    // Profile
    object ChangePassword : Routes("changePassword")
    object EditProfile : Routes("editProfile")
}
