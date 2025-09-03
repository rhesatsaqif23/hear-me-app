package com.example.hearme.data.mapper

import com.example.hearme.data.model.DoctorData
import com.example.hearme.domain.model.DoctorDomain
import com.google.firebase.database.DataSnapshot

object DoctorMapper {
    fun mapToDomain(doctorData: DoctorData): DoctorDomain {
        return DoctorDomain(
            did = doctorData.did,
            dName = doctorData.dName,
            specialist = doctorData.specialist,
            rating = doctorData.rating,
            experience = doctorData.experience,
            dPhoneNumber = doctorData.dPhoneNumber,
            clinic = doctorData.clinic,
            address = doctorData.address,
            city = doctorData.city,
            education = doctorData.education,
            photo = doctorData.photo
        )
    }

    fun mapFromSnapshot(snapshot: DataSnapshot): DoctorDomain {
        return DoctorDomain(
            did = snapshot.child("did").getValue(String::class.java) ?: "",
            dName = snapshot.child("dName").getValue(String::class.java) ?: "",
            specialist = snapshot.child("specialist").getValue(String::class.java) ?: "",
            rating = snapshot.child("rating").getValue(Double::class.java) ?: 0.0,
            experience = snapshot.child("experience").getValue(Int::class.java) ?: 0,
            dPhoneNumber = snapshot.child("dPhoneNumber").getValue(String::class.java) ?: "",
            clinic = snapshot.child("clinic").getValue(String::class.java) ?: "",
            address = snapshot.child("address").getValue(String::class.java) ?: "",
            city = snapshot.child("city").getValue(String::class.java) ?: "",
            education = snapshot.child("education").children.mapNotNull {
                it.getValue(String::class.java)
            },
            photo = snapshot.child("photo").getValue(String::class.java) ?: ""
        )
    }
}
