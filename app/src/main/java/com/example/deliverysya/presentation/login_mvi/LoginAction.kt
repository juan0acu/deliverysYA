package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.MainActivity

internal sealed class LoginAction() {

    data class GetSingWhitEmailAndPassword(val user: String, val Password: String) : LoginAction ()
}