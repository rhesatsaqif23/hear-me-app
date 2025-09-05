package com.example.hearme.presentation.consultation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.component.ChatListItem
import com.example.hearme.presentation.component.SearchBar
import com.example.hearme.presentation.component.TopBar

@Composable
fun InboxScreen(
    navController: NavController,
) {
    var searchValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Kotak Masuk",
                onBackClick = {
                    navController.popBackStack("consultation", inclusive = false)
                },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            SearchBar(
                value = searchValue,
                onValueChange = { searchValue = it },
                placeholder = "Cari dokter",
                onSearchClick = { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            val dummyDoctor = DoctorDomain(
                did = "1",
                dName = "dr. Tirta, S.Psi",
                specialist = "Psikiater, Kejiwaan",
                rating = 4.5,
                experience = 10,
                clinic = "Klinik Sehat Raya",
                address = "Jl. Bunga Dahlia No. 105",
                city = "Jakarta Timur",
                education = listOf("S1 – Psikologi, Universitas Indonesia"),
                dPhoneNumber = "08123456789",
                photo = R.drawable.doctor_1
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    ChatListItem(
                        doctor = dummyDoctor,
                        message = "Halo, selamat pagi",
                        time = "10.15",
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InboxScreenPreview() {
    val navController = rememberNavController()
    InboxScreen(navController = navController)
}
