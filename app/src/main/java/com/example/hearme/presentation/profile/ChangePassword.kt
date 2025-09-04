package com.example.hearme.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.presentation.component.AuthInputText
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.Typography

@Composable
fun ChangePasswordScreen(
    navController: NavController,
    onBackClick: () -> Unit = {}
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Ubah Kata Sandi",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Atur kata sandi baru agar keamanan akun Anda terjamin. " +
                        "Pastikan Anda mengingat kata sandi baru Anda.",
                style = Typography.bodyMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Password Lama",
                style = Typography.bodyLarge
            )
            AuthInputText(
                value = oldPassword,
                onValueChange = { oldPassword = it },
                placeholder = "Masukkan kata sandi",
                isPassword = true
            )

            Text(
                text = "Password Baru",
                style = Typography.bodyLarge
            )
            AuthInputText(
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = "Masukkan kata sandi",
                isPassword = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController)
}
