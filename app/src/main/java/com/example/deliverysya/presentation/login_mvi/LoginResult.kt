package com.example.deliverysya.presentation.login_mvi

sealed class LoginResult {
    sealed class GetSingWhitEmailAndPasswordResult: LoginResult(){
        object InProgress: GetSingWhitEmailAndPasswordResult()
        data class Success(val verified: Boolean): GetSingWhitEmailAndPasswordResult()
        data class Error(val error: String): GetSingWhitEmailAndPasswordResult()
        data class IncorrectCredentials(val message: String): GetSingWhitEmailAndPasswordResult()
        data class EmptyValues(val emptyvalue: String?): GetSingWhitEmailAndPasswordResult()
    }
}