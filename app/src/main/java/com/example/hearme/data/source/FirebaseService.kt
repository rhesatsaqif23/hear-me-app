package com.example.hearme.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {

    // Inisialisasi FirebaseAuth
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    // Inisialisasi Firebase Realtime Database
    val db: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance("https://hearme-5978a-default-rtdb.asia-southeast1.firebasedatabase.app/")
    }
}
