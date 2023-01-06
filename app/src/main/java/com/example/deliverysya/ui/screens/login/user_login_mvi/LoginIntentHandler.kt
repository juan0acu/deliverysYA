package com.example.deliverysya.ui.screens.login.user_login_mvi

import com.example.deliverysya.presentation.login_mvi.LoginUIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginIntentHandler {
    private val userIntents = MutableSharedFlow<LoginUIntent>()
    var coroutineScope: CoroutineScope? = null

    internal fun userIntents(): Flow<LoginUIntent> = userIntents.asSharedFlow()

    fun getSingWhitEmailAndPassword(user: String, pass: String) {
        coroutineScope?.launch {
            userIntents.emit(LoginUIntent.PressingBtnGetInto(user,pass))
        }
    }

}