package com.example.hearme.data.repository

import android.util.Log
import com.example.hearme.data.model.UserData
import com.example.hearme.data.source.firebase.FirebaseService
import com.example.hearme.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // Registrasi user baru
    override fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String,
        gender: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.getReference("users").child(userId)

                        val userData = UserData(
                            uid = userId,
                            name = name,
                            email = email,
                            phoneNumber = phoneNumber,
                            gender = gender
                        )

                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }
                            .addOnFailureListener {
                                user.delete().addOnCompleteListener {
                                    onResult(false, "Gagal menyimpan data user, coba lagi nanti.")
                                }
                            }
                    }
                } else {
                    val errorMessage = task.exception?.message
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }

    // Login user
    override fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    val errorMessage = task.exception?.message
                    Log.e("LoginError", "Login gagal: $errorMessage")
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }

    // Cek password user (re-authenticate)
    override fun checkPassword(password: String, onResult: (Boolean) -> Unit) {
        val email = auth.currentUser?.email
        if (email != null) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        } else {
            onResult(false)
        }
    }

    // Logout user
    override fun logout() {
        auth.signOut()
    }

    // Reset password
    override fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, "Gagal mengirim kode reset password")
                }
            }
    }

    // Mapping pesan error
    private fun getLocalizedErrorMessage(errorMessage: String?): String {
        return when {
            errorMessage?.contains("The email address is badly formatted") == true ->
                "Format email salah"

            errorMessage?.contains("The supplied auth credential is incorrect") == true ->
                "Silahkan periksa kembali email dan password Anda"

            errorMessage?.contains("A network error") == true ->
                "Terjadi kesalahan jaringan"

            else -> errorMessage ?: "Terjadi kesalahan saat login"
        }
    }
}
