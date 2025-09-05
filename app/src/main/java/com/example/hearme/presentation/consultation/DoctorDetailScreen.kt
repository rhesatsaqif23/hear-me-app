package com.example.hearme.presentation.consultation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
                    .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 20.dp)
            ) {
                MainButton(
                    text = "Jadwalkan Konsultasi",
                    onClick = {
                        navController.navigate("schedule/${doctor.did}")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Box {
                Image(
                    painter = painterResource(id = doctor.photo),
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
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = doctor.dName,
                        style = Typography.titleLarge,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = doctor.specialist,
                            style = Typography.titleMedium,
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
                                    style = Typography.bodyLarge,
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
                                    style = Typography.bodyLarge,
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))
                    Divider(thickness = 1.dp, color = Color.Black)
                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = doctor.clinic,
                        style = Typography.titleMedium,
                        color = Color.Black
                    )
                    Text(
                        text = "${doctor.address}, ${doctor.city}",
                        style = Typography.bodyLarge,
                        color = Color.Gray
                    )

                    Text(
                        text = "Riwayat Pendidikan",
                        style = Typography.titleMedium,
                        color = Color.Black
                    )

                    doctor.education.forEach { edu ->
                        Text(
                            text = edu,
                            style = Typography.bodyLarge,
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
        photo = R.drawable.doctor_1
    )
    DoctorDetailScreen(navController, doctorDummy)
}
