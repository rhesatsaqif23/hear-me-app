package com.example.hearme.cache

import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.R

object ListDoctor {
    val doctors = listOf(
        DoctorDomain(
            did = "1",
            dName = "dr. Tirta, S.Psi",
            specialist = "Psikiater, Kejiwaan",
            rating = 4.5,
            experience = 10,
            clinic = "Klinik Sehat Raya, Jakarta",
            address = "Jl. Bunga Dahlia No. 105",
            city = "Jakarta Timur",
            education = listOf(
                "S1 – Psikologi, Universitas Indonesia",
                "S1 – Kedokteran, Universitas Indonesia",
                "Sp.KJ – Spesialis Kedokteran Jiwa, Universitas Gadjah Mada"
            ),
            dPhoneNumber = "08123456789",
            photo = R.drawable.doctor_1
        ),
        DoctorDomain(
            did = "2",
            dName = "dr. Sinta, Sp.KJ",
            specialist = "Psikiater",
            rating = 4.8,
            experience = 7,
            clinic = "Mental Health Center",
            address = "Jl. Merdeka No. 8",
            city = "Jakarta Barat",
            education = listOf(
                "S1 – Psikologi, Universitas Airlangga",
                "S1 – Kedokteran, Universitas Indonesia",
                "Sp.KJ – Spesialis Kedokteran Jiwa, Universitas Indonesia"
            ),
            dPhoneNumber = "08234567890",
            photo = R.drawable.doctor_2
        ),
        DoctorDomain(
            did = "3",
            dName = "dr. Maya, S.Psi",
            specialist = "Psikolog Anak",
            rating = 4.7,
            experience = 6,
            clinic = "Happy Mind Center",
            address = "Jl. Diponegoro No. 10",
            city = "Jakarta Selatan",
            education = listOf(
                "S1 – Psikologi, Universitas Diponegoro",
                "M.Psi – Magister Psikologi, Universitas Indonesia",
                "Konsentrasi – Psikologi Perkembangan Anak"
            ),
            dPhoneNumber = "08456789012",
            photo = R.drawable.doctor_3
        ),
        DoctorDomain(
            did = "4",
            dName = "dr. Andi, S.Psi",
            specialist = "Psikolog Klinis",
            rating = 4.6,
            experience = 4,
            clinic = "MindCare Clinic",
            address = "Jl. Sudirman No. 5",
            city = "Tangerang",
            education = listOf(
                "S1 – Psikologi, Universitas Gadjah Mada",
                "M.Psi – Magister Psikologi Klinis, Universitas Gadjah Mada"
            ),
            dPhoneNumber = "08345678901",
            photo = R.drawable.doctor_4
        )
    )
}