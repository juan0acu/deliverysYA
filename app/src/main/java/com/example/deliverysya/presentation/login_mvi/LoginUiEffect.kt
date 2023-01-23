package com.example.deliverysya.presentation.login_mvi

internal sealed class LoginUiEffect {
    data class ErrorUiEffect(val error: String) : LoginUiEffect()
    object LoadingUiEffect: LoginUiEffect()
}