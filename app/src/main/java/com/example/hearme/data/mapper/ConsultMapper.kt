package com.example.hearme.data.mapper

import com.example.hearme.data.model.ConsultData
import com.example.hearme.domain.model.ConsultDomain
import com.google.firebase.database.DataSnapshot

object ConsultMapper {

    fun mapToDomain(consultData: ConsultData): ConsultDomain {
        return ConsultDomain(
            cid = consultData.cid,
            did = consultData.did,
            uid = consultData.uid,
            date = consultData.date,
            time = consultData.time,
            payment = consultData.payment,
            clinic = consultData.clinic
        )
    }

    fun mapFromSnapshot(snapshot: DataSnapshot): ConsultDomain {
        return ConsultDomain(
            cid = snapshot.child("cid").getValue(String::class.java) ?: "",
            did = snapshot.child("did").getValue(String::class.java) ?: "",
            uid = snapshot.child("uid").getValue(String::class.java) ?: "",
            date = snapshot.child("date").getValue(String::class.java) ?: "",
            time = snapshot.child("time").getValue(String::class.java) ?: "",
            payment = snapshot.child("payment").getValue(String::class.java) ?: "",
            clinic = snapshot.child("clinic").getValue(String::class.java) ?: ""
        )
    }
}
