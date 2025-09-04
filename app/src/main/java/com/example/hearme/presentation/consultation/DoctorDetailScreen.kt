package com.example.hearme.presentation.consultation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.blue
import com.example.hearme.ui.theme.green

@Composable
fun DoctorDetailScreen(
    navController: NavController,
    doctor: DoctorDomain,
    onBackClick: () -> Unit = { navController.popBackStack() },
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                MainButton(
                    text = "Jadwalkan Konsultasi",
                    onClick = {
                        navController.navigate("schedule")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // Foto dokter + TopBar overlay
            Box {
                Image(
                    painter = painterResource(id = R.drawable.doctor_placeholder),
                    contentDescription = "Foto Dokter",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                )

                TopBar(
                    title = "",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp)
                )
            }

            // Konten detail dokter
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Nama dokter
                    Text(
                        text = doctor.dName,
                        style = Typography.titleLarge,
                        color = Color.Black
                    )
                    // Specialist + rating + experience
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = doctor.specialist,
                            style = Typography.bodyLarge,
                            color = Color.Gray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_like),
                                    contentDescription = "Rating",
                                    tint = green,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = doctor.rating.toString(),
                                    style = Typography.labelLarge,
                                    color = Color.Black
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_work),
                                    contentDescription = "Pengalaman",
                                    tint = blue,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${doctor.experience} tahun",
                                    style = Typography.labelLarge,
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))
                    Divider(thickness = 1.dp, color = Color.Black)
                    Spacer(modifier = Modifier.height(2.dp))

                    // Klinik
                    Text(
                        text = doctor.clinic,
                        style = Typography.bodyLarge,
                        color = Color.Black
                    )
                    Text(
                        text = "${doctor.address}, ${doctor.city}",
                        style = Typography.labelLarge,
                        color = Color.Gray
                    )

                    // Riwayat Pendidikan
                    Text(
                        text = "Riwayat Pendidikan",
                        style = Typography.bodyLarge,
                        color = Color.Black
                    )
                    doctor.education.forEach { edu ->
                        Text(
                            text = edu,
                            style = Typography.labelLarge,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorDetailScreenPreview() {
    val navController = rememberNavController()
    val doctorDummy = DoctorDomain(
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
            "S1 – Kedokteran, Universitas Indonesia"
        ),
        dPhoneNumber = "08123456789",
        photo = "https://i.imgur.com/mwL6QF5.jpeg"
    )
    DoctorDetailScreen(navController, doctorDummy)
}
