package com.example.hearme.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hearme.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // State untuk input data user
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender

    // State untuk hasil proses
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _isResetPassword = MutableStateFlow(false)
    val isResetPassword: StateFlow<Boolean> = _isResetPassword

    fun updateName(value: String) {
        _name.value = value
    }

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateConfirmPassword(value: String) {
        _confirmPassword.value = value
    }

    fun updatePhoneNumber(value: String) {
        _phoneNumber.value = value
    }

    fun updateGender(value: String) {
        _gender.value = value
    }

    fun clearNameInput() {
        _name.value = ""
    }

    fun clearEmailInput() {
        _email.value = ""
    }

    fun clearPhoneNumberInput() {
        _phoneNumber.value = ""
    }

    fun clearPasswordInput() {
        _password.value = ""
    }

    fun clearConfirmPasswordInput() {
        _confirmPassword.value = ""
    }

    fun clearGenderInput() {
        _gender.value = ""
    }

    fun resetErrorMessage() {
        _errorMessage.value = null
    }

    fun resetIsRegistered() {
        _isRegistered.value = false
    }

    fun resetIsLoggedIn() {
        _isLoggedIn.value = false
    }

    fun resetIsResetPassword() {
        _isResetPassword.value = false
    }

    fun register() {
        if (_password.value != _confirmPassword.value) {
            _errorMessage.value = "Password dan Confirm Password tidak cocok"
            return
        }

        viewModelScope.launch {
            authUseCase.register(
                name.value,
                email.value,
                password.value,
                phoneNumber.value,
                gender.value
            ) { success, message ->
                if (success) {
                    _isRegistered.value = true
                } else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            authUseCase.login(
                email.value, password.value
            ) { success, message ->
                if (success) {
                    _isLoggedIn.value = true
                } else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun checkPassword(onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            authUseCase.checkPassword(password.value) { success ->
                if (success) {
                    onResult(true, "")
                } else {
                    onResult(false, "Password salah")
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authUseCase.logoutUser()
            _isLoggedIn.value = false
        }
    }

    fun resetPassword() {
        viewModelScope.launch {
            authUseCase.resetPassword(email.value) { success, message ->
                if (success) {
                    _isResetPassword.value = true
                } else {
                    _errorMessage.value = message
                }
            }
        }
    }

    class Factory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
