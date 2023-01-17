package com.example.deliverysya.ui.screens.user_register.register_user_mvi

import com.example.deliverysya.presentation.login_mvi.LoginUIntent
import com.example.deliverysya.presentation.register_user_mvi.RegisterUserUItent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterUserIntentHandler {
    private val userIntents = MutableSharedFlow<RegisterUserUItent>()
    var coroutineScope: CoroutineScope? = null

    internal fun userIntents(): Flow<RegisterUserUItent> = userIntents.asSharedFlow()

    fun pressingBtnRegisterUserIntent(email: String, pass: String, pass2:String, name: String) {
        coroutineScope?.launch {
            userIntents.emit(RegisterUserUItent.PressingBtnRegisterUser(email,pass,pass2,name))
        }
    }
}