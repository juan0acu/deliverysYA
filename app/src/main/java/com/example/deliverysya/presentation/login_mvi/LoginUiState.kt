package com.example.deliverysya.presentation.login_mvi

internal sealed class LoginUiState {
    object DefaultUiState : LoginUiState()
    object SuccessUiState : LoginUiState()
}