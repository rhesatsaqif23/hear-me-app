package com.example.hearme.domain.usecase

import com.example.hearme.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        gender: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        // Validasi input
        if (name.isBlank() || email.isBlank() || password.isBlank() || phoneNumber.isBlank() || gender.isBlank()) {
            onResult(false, "Harap isi semua data sebelum mendaftar")
            return
        }

        // Validasi email
        if (!validateEmail(email)) {
            onResult(false, "Email tidak valid")
            return
        }

        // Validasi password
        if (!validatePass(password)) {
            onResult(false, "Password harus minimal 8 karakter, mengandung huruf & angka")
            return
        }

        // Validasi nomor HP
        if (!validateNumber(phoneNumber)) {
            onResult(false, "Nomor HP harus terdiri dari minimal 8 karakter")
            return
        }

        // Jika validasi lolos → register lewat repository
        authRepository.register(name, email, password, phoneNumber, gender, onResult)
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Harap isi semua data sebelum masuk")
            return
        }

        authRepository.login(email, password, onResult)
    }

    fun checkPassword(password: String, onResult: (Boolean) -> Unit) {
        authRepository.checkPassword(password, onResult)
    }

    fun logoutUser() {
        authRepository.logout()
    }

    fun resetPassword(
        email: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        authRepository.resetPassword(email, onResult)
    }


    private fun validateEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun validatePass(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isDigit() } &&
                password.any { it.isLetter() }
    }

    private fun validateNumber(number: String): Boolean {
        return number.length >= 8 && number.all { it.isDigit() }
    }
}
