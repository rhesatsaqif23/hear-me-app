package com.example.hearme.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hearme.R
import com.example.hearme.presentation.component.AuthInputText
import com.example.hearme.presentation.component.LoginByButton
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.ui.theme.*
import com.example.hearme.ui.theme.Typography

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController? = null
) {
    val context = LocalContext.current

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isRegistered by viewModel.isRegistered.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(VioletNormal, Color.White),
                    startY = 100f,
                    endY = 700f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 110.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Daftarkan Akun",
                    style = Typography.titleLarge.copy(fontSize = 24.sp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Buat akun baru Anda untuk memulai pengalaman menakjubkan bersama kami",
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                AuthInputText(
                    value = name,
                    onValueChange = { viewModel.updateName(it) },
                    placeholder = "Nama",
                    icon = R.drawable.ic_username
                )

                Spacer(modifier = Modifier.height(8.dp))

                AuthInputText(
                    value = email,
                    onValueChange = { viewModel.updateEmail(it) },
                    placeholder = "Email",
                    icon = R.drawable.ic_email
                )

                Spacer(modifier = Modifier.height(8.dp))

                AuthInputText(
                    value = password,
                    onValueChange = { viewModel.updatePassword(it) },
                    placeholder = "Kata sandi",
                    icon = R.drawable.ic_password,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                AuthInputText(
                    value = confirmPassword,
                    onValueChange = { viewModel.updateConfirmPassword(it) },
                    placeholder = "Konfirmasi kata sandi",
                    icon = R.drawable.ic_password,
                    isPassword = true
                )
            }

            // Footer
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MainButton(
                    text = "Daftar",
                    onClick = { viewModel.register() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Sudah punya akun? ",
                        style = Typography.labelLarge,
                        color = grey3
                    )
                    Text(
                        text = "Masuk di sini",
                        style = Typography.labelLarge.copy(color = VioletNormal),
                        modifier = Modifier.clickable {
                            navController?.navigate("login")
                        }
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = grey2
                )

                Text(
                    text = "atau daftar menggunakan akun",
                    style = Typography.labelLarge,
                    color = grey3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    LoginByButton(iconRes = R.drawable.google)
                    LoginByButton(iconRes = R.drawable.apple)
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    style = Typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    LaunchedEffect(isRegistered) {
        if (isRegistered) {
            Toast.makeText(context, "Register berhasil, silakan login", Toast.LENGTH_SHORT).show()
            navController?.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
            viewModel.resetIsRegistered()
        }
    }

    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.resetErrorMessage()
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    RegisterScreen(navController = null)
//}