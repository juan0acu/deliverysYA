package com.example.deliverysya.presentation.register_user_mvi

internal sealed class RegisterUserResult {
    sealed class RegisterWhitEmailAndPassResult : RegisterUserResult(){
        object InProgress: RegisterWhitEmailAndPassResult()
        data class Success(val verified: Boolean): RegisterWhitEmailAndPassResult()
        data class Error(val error: String): RegisterWhitEmailAndPassResult()
        data class EmptyValues(val emptyvalue: String): RegisterWhitEmailAndPassResult()
    }
}