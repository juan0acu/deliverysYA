package com.example.deliverysya.presentation.login_mvi

sealed class LoginUIntent {
    data class PressingBtnGetInto(val user: String , val password : String) : LoginUIntent()
}