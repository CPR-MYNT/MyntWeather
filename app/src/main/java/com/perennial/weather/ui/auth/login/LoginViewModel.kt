package com.perennial.weather.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.perennial.weather.domain.usecase.LoginUseCase
import com.perennial.weather.domain.usecase.Result
import com.perennial.weather.utils.getMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onLoginClick(navController : NavHostController, onSuccess : () -> Unit){
        viewModelScope.launch {
            _isLoading.value = true
            _message.value = ""
            
            when (val result = loginUseCase(email = email.value, password = password.value)) {
                is Result.Success -> {
                    _message.value = ""
                    onSuccess()
                }
                is Result.Error -> {
                    _message.value = result.error.getMessage(navController.context)
                }
            }
            
            _isLoading.value = false
        }
    }



}