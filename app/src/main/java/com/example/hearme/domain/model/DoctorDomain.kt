package com.example.hearme.domain.model

data class DoctorDomain(
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
    val photo: String = "",
)
