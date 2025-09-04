package com.example.hearme.presentation.consultation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.component.*
import com.example.hearme.ui.theme.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScheduleScreen(
    navController: NavController,
    doctor: DoctorDomain,
    onBackClick: () -> Unit = {}
) {
    var selectedDate by remember { mutableStateOf("Hari ini") }
    var selectedTime by remember { mutableStateOf("09.00") }
    var isQrisChecked by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Jadwalkan Konsultasi",
                onBackClick = onBackClick,
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
                    text = "Chat Dokter",
                    onClick = {}
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Dokter
            DoctorCardHorizontal(
                doctor = doctor,
                onClick = {}
            )

            // Pilih tanggal konsultasi
            Text("Pilih tanggal konsultasi", style = Typography.titleSmall)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val dates = listOf("Hari ini", "Jum 5 Sept", "Sab 6 Sept", "Min 7 Sept")
                dates.forEach { date ->
                    DateButton(
                        value = date,
                        isSelected = selectedDate == date,
                        onClick = { selectedDate = date }
                    )
                }
            }

            // Pilih waktu konsultasi
            Text("Pilih waktu konsultasi", style = Typography.titleSmall)
            val times = listOf("09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00")
            FlowRow(
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                times.forEach { time ->
                    TimeButton(
                        value = time,
                        isSelected = selectedTime == time,
                        onClick = { selectedTime = time }
                    )
                }
            }

            // Pilih metode pembayaran
            Text("Pilih metode pembayaran", style = Typography.titleSmall)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.qris),
                        contentDescription = "QRIS",
                        modifier = Modifier.height(28.dp)
                    )
                }

                Checkbox(
                    checked = isQrisChecked,
                    onCheckedChange = { isQrisChecked = it }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    val navController = rememberNavController()
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
        photo = "https://i.imgur.com/mwL6QF5.jpeg"
    )
    ScheduleScreen(navController, doctorDummy)
}
