package com.example.hearme.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hearme.R

val plusjakartasans = FontFamily(Font(R.font.plusjakartasans))

val Typography = Typography(
    // Body
    bodyLarge = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = plusjakartasans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    )
)
