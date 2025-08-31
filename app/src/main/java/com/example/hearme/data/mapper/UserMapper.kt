package com.example.hearme.data.mapper

import com.example.hearme.data.model.UserData
import com.example.hearme.domain.model.UserDomain
import com.google.firebase.database.DataSnapshot

object UserMapper {
    fun mapToDomain(userData: UserData): UserDomain {
        return UserDomain(
            uid = userData.uid,
            name = userData.name,
            email = userData.email,
            phoneNumber = userData.phoneNumber,
            gender = userData.gender
        )
    }

    fun mapFromSnapshot(snapshot: DataSnapshot): UserDomain {
        return UserDomain(
            uid = snapshot.child("uid").getValue(String::class.java) ?: "",
            name = snapshot.child("name").getValue(String::class.java) ?: "",
            email = snapshot.child("email").getValue(String::class.java) ?: "",
            phoneNumber = snapshot.child("phoneNumber").getValue(String::class.java) ?: "",
            gender = snapshot.child("gender").getValue(String::class.java) ?: ""
        )
    }
}