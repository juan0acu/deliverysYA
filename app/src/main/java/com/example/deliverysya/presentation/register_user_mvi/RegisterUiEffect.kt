package com.example.deliverysya.presentation.register_user_mvi

internal sealed class RegisterUiEffect {
    data class ErrorUiEffect(val error: String) : RegisterUiEffect()
    object LoadingUiEffect: RegisterUiEffect()
}