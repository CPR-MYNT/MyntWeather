package com.perennial.weather.utils

import android.content.Context
import com.perennial.weather.R
import com.perennial.weather.domain.usecase.AuthError

fun AuthError.getMessage(context: Context): String {
    return when (this) {
        is AuthError.EmptyName -> context.getString(R.string.enter_name)
        is AuthError.EmptyEmail -> context.getString(R.string.enter_email)
        is AuthError.InvalidEmail -> context.getString(R.string.enter_valid_email)
        is AuthError.EmptyPassword -> context.getString(R.string.enter_password)
        is AuthError.InvalidPassword -> context.getString(R.string.enter_valid_password)
        is AuthError.EmptyConfirmPassword -> context.getString(R.string.enter_confirm_password)
        is AuthError.PasswordMismatch -> context.getString(R.string.enter_valid_confirm_password)
        is AuthError.InvalidCredentials -> context.getString(R.string.invalid_credentials)
        is AuthError.UserAlreadyExists -> context.getString(R.string.user_already_exists)
        is AuthError.UnknownError -> {
            val baseMessage = context.getString(R.string.error_occurred)
            if (message != null) {
                "$baseMessage: $message"
            } else {
                baseMessage
            }
        }
    }
}

