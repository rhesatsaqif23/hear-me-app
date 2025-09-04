package com.example.hearme.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.ProfileInputText
import com.example.hearme.presentation.component.TopBar

@Composable
fun EditProfileScreen(
    navController: NavController,
    onBackClick: () -> Unit = {}
) {
    var nama by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var noTelp by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Edit Profil",
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
                    text = "Simpan",
                    onClick = { }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.photo_profile),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            ProfileInputText(
                label = "Nama Lengkap",
                value = nama,
                onValueChange = { nama = it }
            )
            ProfileInputText(
                label = "Tanggal Lahir",
                value = tanggalLahir,
                onValueChange = { tanggalLahir = it }
            )
            ProfileInputText(
                label = "Jenis Kelamin",
                value = jenisKelamin,
                onValueChange = { jenisKelamin = it }
            )
            ProfileInputText(
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )
            ProfileInputText(
                label = "Nomor Telepon",
                value = noTelp,
                onValueChange = { noTelp = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    EditProfileScreen(navController)
}