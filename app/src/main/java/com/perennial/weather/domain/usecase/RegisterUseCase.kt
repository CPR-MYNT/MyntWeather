package com.perennial.weather.domain.usecase

import com.perennial.weather.domain.repository.AuthRepository
import com.perennial.weather.utils.Utils

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Boolean> {
        return try {
            if (name.isBlank()) {
                return Result.Error(AuthError.EmptyName)
            }
            
            if (email.isBlank()) {
                return Result.Error(AuthError.EmptyEmail)
            }
            
            if (!Utils.isValidEmail(email)) {
                return Result.Error(AuthError.InvalidEmail)
            }
            
            if (password.isBlank()) {
                return Result.Error(AuthError.EmptyPassword)
            }
            
            if (password.length !in 8..16) {
                return Result.Error(AuthError.InvalidPassword)
            }
            
            if (confirmPassword.isBlank()) {
                return Result.Error(AuthError.EmptyConfirmPassword)
            }
            
            if (password != confirmPassword) {
                return Result.Error(AuthError.PasswordMismatch)
            }
            
            val success = authRepository.register(
                name = name.trim(),
                email = email.trim(),
                password = password.trim()
            )
            
            if (success) {
                Result.Success(true)
            } else {
                Result.Error(AuthError.UserAlreadyExists)
            }
        } catch (e: Exception) {
            Result.Error(AuthError.UnknownError(e.message))
        }
    }
}

