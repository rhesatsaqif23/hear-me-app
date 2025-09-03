package com.example.hearme.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hearme.presentation.component.AuthInputText

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController? = null
) {
    val context = LocalContext.current

    // state dari ViewModel
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge
        )

        AuthInputText(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = "Email"
        )

        AuthInputText(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = "Password"
        )

        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

    // efek samping: sukses
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            Toast.makeText(context, "Login berhasil", Toast.LENGTH_SHORT).show()
            navController?.navigate("dashboard  ") {
                popUpTo("login") { inclusive = true }
            }
            viewModel.resetIsLoggedIn()
        }
    }

    // efek samping: error
    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.resetErrorMessage()
        }
    }
}
