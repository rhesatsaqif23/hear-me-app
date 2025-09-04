package com.example.hearme.presentation.navigation

import com.example.hearme.R

sealed class BottomNavItem(
    val route: String,
    val iconOn: Int,
    val iconOff: Int
) {
    object Dashboard : BottomNavItem(
        route = "dashboard",
        iconOn = R.drawable.ic_home_on,
        iconOff = R.drawable.ic_home_off
    )

    object Analysis : BottomNavItem(
        route = "voiceRecord",
        iconOn = R.drawable.ic_grid_on,
        iconOff = R.drawable.ic_grid_off
    )

    object Consultation : BottomNavItem(
        route = "consultation",
        iconOn = R.drawable.ic_doctor_on,
        iconOff = R.drawable.ic_doctor_off
    )

    object Exercise : BottomNavItem(
        route = "exercise",
        iconOn = R.drawable.ic_exercise_on,
        iconOff = R.drawable.ic_exercise_off
    )

    object Profile : BottomNavItem(
        route = "profile",
        iconOn = R.drawable.ic_settings_on,
        iconOff = R.drawable.ic_settings_off
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Dashboard,
    BottomNavItem.Analysis,
    BottomNavItem.Consultation,
    BottomNavItem.Exercise,
    BottomNavItem.Profile
)