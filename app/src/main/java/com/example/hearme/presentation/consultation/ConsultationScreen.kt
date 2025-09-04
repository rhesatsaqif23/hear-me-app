package com.example.hearme.presentation.consultation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.presentation.component.DoctorCard
import com.example.hearme.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(
    navController: NavController,
    currentRoute: String = "consultation",
) {
    val doctors = listOf(
        DoctorDomain(
            did = "1",
            dName = "dr. Tirta, S.Psi",
            specialist = "Psikiater",
            rating = 4.5,
            experience = 5,
            clinic = "Sehat Jiwa Clinic",
            address = "Jl. Kebon Jeruk No. 12",
            city = "Jakarta Timur",
            education = listOf("S.Psi - UI", "Sp.KJ - UGM"),
            dPhoneNumber = "08123456789",
            photo = ""
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
            education = listOf("S.Psi - UNAIR", "Sp.KJ - UI"),
            dPhoneNumber = "08234567890",
            photo = ""
        ),
        DoctorDomain(
            did = "3",
            dName = "dr. Andi, S.Psi",
            specialist = "Psikolog Klinis",
            rating = 4.6,
            experience = 4,
            clinic = "MindCare Clinic",
            address = "Jl. Sudirman No. 5",
            city = "Tangerang",
            education = listOf("S.Psi - UGM"),
            dPhoneNumber = "08345678901",
            photo = ""
        ),
        DoctorDomain(
            did = "4",
            dName = "dr. Maya, S.Psi",
            specialist = "Psikolog Anak",
            rating = 4.7,
            experience = 6,
            clinic = "Happy Mind Center",
            address = "Jl. Diponegoro No. 10",
            city = "Jakarta Selatan",
            education = listOf("S.Psi - Undip", "M.Psi - UI"),
            dPhoneNumber = "08456789012",
            photo = ""
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat dengan Dokter",
                        style = Typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("inbox")
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "Chat Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                )
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 80.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(doctors) { doctor ->
                DoctorCard(
                    doctor = doctor,
                    onClick = {
                        navController.navigate("doctorDetail")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultationScreenPreview() {
    val navController = rememberNavController()
    ConsultationScreen(navController)
}
