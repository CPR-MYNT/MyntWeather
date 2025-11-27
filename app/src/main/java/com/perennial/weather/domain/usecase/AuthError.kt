package com.perennial.weather.domain.usecase

sealed class AuthError {
    object EmptyName : AuthError()
    object EmptyEmail : AuthError()
    object InvalidEmail : AuthError()
    object EmptyPassword : AuthError()
    object InvalidPassword : AuthError()
    object EmptyConfirmPassword : AuthError()
    object PasswordMismatch : AuthError()
    object InvalidCredentials : AuthError()
    object UserAlreadyExists : AuthError()
    data class UnknownError(val message: String?) : AuthError()
//    data class ValidationError(val id: Int) : AuthError()
}

