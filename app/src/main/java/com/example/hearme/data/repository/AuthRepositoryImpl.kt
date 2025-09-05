package com.example.hearme.data.repository

import android.util.Log
import com.example.hearme.data.mapper.UserMapper
import com.example.hearme.data.model.UserData
import com.example.hearme.data.source.firebase.FirebaseService
import com.example.hearme.domain.model.UserDomain
import com.example.hearme.domain.repository.AuthRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AuthRepositoryImpl : AuthRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

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
                            phoneNumber = phoneNumber.ifBlank { "" },
                            gender = gender.ifBlank { "" }
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

    override fun logout() {
        auth.signOut()
    }

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

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    override fun getUserData(uid: String, onResult: (UserDomain?) -> Unit) {
        val userRef = db.getReference("users").child(uid)

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = UserMapper.mapFromSnapshot(snapshot)
                    onResult(user)
                } else {
                    onResult(null)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AuthRepositoryImpl", "getUserData cancelled: ${error.message}")
                onResult(null)
            }
        })
    }

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
