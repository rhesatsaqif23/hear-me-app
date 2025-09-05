package com.example.hearme.data.model

data class DoctorData(
    val did: String = "",
    val dName: String = "",
    val specialist: String = "",
    val rating: Double = 0.0,
    val experience: Int = 0,
    val dPhoneNumber: String = "",
    val clinic: String = "",
    val address: String = "",
    val city: String = "",
    val education: List<String> = emptyList(),
    val photo: Int = 0
)
