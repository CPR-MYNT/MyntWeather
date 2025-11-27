package com.perennial.weather.domain.usecase

import com.perennial.weather.domain.repository.AuthRepository
import com.perennial.weather.utils.Utils

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        return try {
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
            
            val success = authRepository.login(email = email.trim(), password = password.trim())
            
            if (success) {
                Result.Success(true)
            } else {
                Result.Error(AuthError.InvalidCredentials)
            }
        } catch (e: Exception) {
            Result.Error(AuthError.UnknownError(e.message))
        }
    }
}

