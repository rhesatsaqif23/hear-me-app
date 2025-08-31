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
fun RegisterScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController? = null
) {
    val context = LocalContext.current

    // collect state dari ViewModel
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isRegistered by viewModel.isRegistered.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge
        )

        AuthInputText(
            value = name,
            onValueChange = { viewModel.updateName(it) },
            placeholder = "Full Name"
        )
        AuthInputText(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = "Email"
        )
        AuthInputText(
            value = phoneNumber,
            onValueChange = { viewModel.updatePhoneNumber(it) },
            placeholder = "Phone Number"
        )
        AuthInputText(
            value = gender,
            onValueChange = { viewModel.updateGender(it) },
            placeholder = "Gender"
        )
        AuthInputText(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = "Password"
        )
        AuthInputText(
            value = confirmPassword,
            onValueChange = { viewModel.updateConfirmPassword(it) },
            placeholder = "Confirm Password"
        )

        Button(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }

        // Debug error
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

    // Handle efek samping (success / error)
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
