package com.example.hearme.presentation.consultation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.ConsultDomain
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.component.*

@Composable
fun SuccessScreen(
    navController: NavController,
    doctor: DoctorDomain,
    consult: ConsultDomain
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Pembayaran Sukses",
                onBackClick = {
                    navController.popBackStack("consultation", inclusive = false)
                },
                modifier = Modifier
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                MainButton(
                    text = "Mulai Chat Dokter",
                    onClick = {
                        navController.navigate("inbox")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ConsultCard(
                doctor = doctor,
                consult = consult,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    val doctorDummy = DoctorDomain(
        did = "1",
        dName = "dr. Tirta, S.Psi",
        specialist = "Psikiater, Kejiwaan",
        rating = 4.5,
        experience = 10,
        clinic = "Sehat Jiwa Clinic",
        address = "Jl. Kebon Jeruk No. 12",
        city = "Jakarta Timur",
        education = listOf("S.Psi - UI", "Sp.KJ - UGM"),
        dPhoneNumber = "08123456789",
        photo = R.drawable.doctor_1
    )

    val consultDummy = ConsultDomain(
        cid = "C001",
        did = "1",
        dName = "dr. Tirta, S.Psi",
        date = "Jumat, 5 September 2025",
        time = "11.00 WIB",
        payment = "QRIS",
        clinic = "Klinik Sehat Raya, Jakarta Timur"
    )

    SuccessScreen(
        navController = rememberNavController(),
        doctor = doctorDummy,
        consult = consultDummy,
    )
}
