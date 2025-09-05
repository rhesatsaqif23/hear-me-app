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
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController? = null
) {
    val context = LocalContext.current

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

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
                .padding(horizontal = 20.dp, vertical = 150.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Selamat Datang",
                    style = Typography.titleLarge.copy(fontSize = 24.sp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Masukkan email dan kata sandi yang telah terdaftar untuk menjaga keamanan akun Anda",
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                AuthInputText(
                    value = email,
                    onValueChange = { viewModel.updateEmail(it) },
                    placeholder = "Email",
                    icon = R.drawable.ic_email
                )

                Spacer(modifier = Modifier.height(2.dp))

                AuthInputText(
                    value = password,
                    onValueChange = { viewModel.updatePassword(it) },
                    placeholder = "Kata sandi",
                    icon = R.drawable.ic_password,
                    isPassword = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        var checked by remember { mutableStateOf(false) }
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                        Text(
                            text = "Ingat saya",
                            style = Typography.labelLarge,
                            color = grey3
                        )
                    }
                    Text(
                        text = "Lupa kata sandi",
                        style = Typography.labelLarge,
                        color = VioletNormal,
                        modifier = Modifier.clickable { }
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MainButton(
                    text = "Masuk",
                    onClick = { viewModel.login() },
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Belum punya akun? ",
                        style = Typography.labelLarge,
                        color = grey3
                    )
                    Text(
                        text = "Daftar di sini",
                        style = Typography.labelLarge.copy(color = VioletNormal),
                        modifier = Modifier.clickable {
                            navController?.navigate("register")
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
                    text = "atau masuk menggunakan akun",
                    style = Typography.labelLarge,
                    color = grey3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
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

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            Toast.makeText(context, "Login berhasil", Toast.LENGTH_SHORT).show()
            navController?.navigate("dashboard") {
                popUpTo("login") { inclusive = true }
            }
            viewModel.resetIsLoggedIn()
        }
    }

    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.resetErrorMessage()
        }
    }
}

//class AuthUseCase(
//    private val authRepository: AuthRepository
//) {
//
//}
//
//class FakeAuthRepository : AuthRepository {
//    suspend fun login(email: String, password: String): Boolean {
//        return true
//    }
//
//    override fun register(
//        name: String,
//        email: String,
//        password: String,
//        phoneNumber: String,
//        gender: String,
//        onResult: (Boolean, String?) -> Unit
//    ) {
//
//    }
//
//    override fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {}
//    override fun checkPassword(password: String, onResult: (Boolean) -> Unit) {}
//    override fun logout() {}
//    override fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {}
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    val fakeUseCase = AuthUseCase(FakeAuthRepository())
//    val fakeViewModel = AuthViewModel(authUseCase = fakeUseCase)
//    LoginScreen(viewModel = fakeViewModel, navController = null)
//}
