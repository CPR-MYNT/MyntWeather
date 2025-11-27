package com.perennial.weather.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.perennial.weather.domain.usecase.RegisterUseCase
import com.perennial.weather.domain.usecase.Result
import com.perennial.weather.utils.getMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun onEmailChange(email: String){
        _email.value = email
    }

    fun onPasswordChange(password: String){
        _password.value = password
    }

    fun onConfirmPasswordChange(confirmPassword : String){
        _confirmPassword.value = confirmPassword
    }

    fun onRegisterClick(navHostController: NavHostController, onSuccess : () -> Unit){
        viewModelScope.launch {
            _isLoading.value = true
            _message.value = ""
            
            when (val result = registerUseCase(
                name = name.value,
                email = email.value,
                password = password.value,
                confirmPassword = confirmPassword.value
            )) {
                is Result.Success -> {
                    _message.value = ""
                    onSuccess()
                }
                is Result.Error -> {
                    _message.value = result.error.getMessage(navHostController.context)
                }
            }
            
            _isLoading.value = false
        }
    }


    }
