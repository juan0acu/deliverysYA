package com.example.deliverysya.presentation.login_mvi

internal sealed class LoginAction {
    data class GetSingWhitEmailAndPasswordAction(val user: String, val Password: String) : LoginAction ()
}