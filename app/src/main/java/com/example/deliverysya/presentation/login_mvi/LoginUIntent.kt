package com.example.deliverysya.presentation.login_mvi

internal sealed class LoginUIntent {
    data class PressingBtnGetInto(val user: String , val password : String) : LoginUIntent()
    //object RetryUIntent : LoginUIntent()
}