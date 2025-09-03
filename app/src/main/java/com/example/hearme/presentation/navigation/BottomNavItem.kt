package com.example.hearme.presentation.navigation

import com.example.hearme.R

sealed class BottomNavItem(
    val route: String,
    val iconOn: Int,
    val iconOff: Int
) {
    object Home : BottomNavItem(
        route = "home",
        iconOn = R.drawable.ic_home_on,
        iconOff = R.drawable.ic_home_off
    )

    object Fitur : BottomNavItem(
        route = "fitur",
        iconOn = R.drawable.ic_grid_on,
        iconOff = R.drawable.ic_grid_off
    )

    object Dokter : BottomNavItem(
        route = "dokter",
        iconOn = R.drawable.ic_doctor_on,
        iconOff = R.drawable.ic_doctor_off
    )

    object Latihan : BottomNavItem(
        route = "latihan",
        iconOn = R.drawable.ic_exercise_on,
        iconOff = R.drawable.ic_exercise_off
    )

    object Profil : BottomNavItem(
        route = "profil",
        iconOn = R.drawable.ic_settings_on,
        iconOff = R.drawable.ic_settings_off
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Fitur,
    BottomNavItem.Dokter,
    BottomNavItem.Latihan,
    BottomNavItem.Profil
)