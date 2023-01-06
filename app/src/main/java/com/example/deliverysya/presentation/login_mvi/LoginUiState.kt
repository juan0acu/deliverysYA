package com.example.deliverysya.presentation.login_mvi

internal sealed class LoginUiState {
    object DefaultUiState : LoginUiState()
    object LoadingUiState : LoginUiState()
    object SuccessUiState : LoginUiState()
    data class ErrorUiState(val error: String): LoginUiState()
}