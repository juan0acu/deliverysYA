package com.example.deliverysya.presentation.register_user_mvi


internal sealed class RegisterUserUiState {
    object DefaultUiState : RegisterUserUiState()
   // object LoadingUiState : RegisterUserUiState()
    object SuccessUiState : RegisterUserUiState()
   // data class ErrorUiState(val error: String) : RegisterUserUiState()
}