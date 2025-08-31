package com.example.hearme.domain.repository

interface AuthRepository {
    fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        gender: String,
        onResult: (Boolean, String?) -> Unit
    )

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    )

    fun checkPassword(
        password: String,
        onResult: (Boolean) -> Unit
    )

    fun logout()

    fun resetPassword(
        email: String,
        onResult: (Boolean, String?) -> Unit
    )
}
