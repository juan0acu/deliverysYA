package com.example.deliverysya.presentation.register_user_mvi

internal sealed class RegisterUserAction {
    data class RegisterWhitEmailAndPassAction(val email: String, val pass: String,val pass2 : String,val name: String) : RegisterUserAction()
}