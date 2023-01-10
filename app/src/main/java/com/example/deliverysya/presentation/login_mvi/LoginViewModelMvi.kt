package com.example.deliverysya.presentation.login_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliverysya.presentation.login_mvi.LoginAction.*
import com.example.deliverysya.presentation.login_mvi.LoginUIntent.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class LoginViewModelMvi: ViewModel() {
    private val reducer = LoginReducer()
    private val processor = LoginProcessor()

    private val loginDefaultUiState: LoginUiState = LoginUiState.DefaultUiState
    private val uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(loginDefaultUiState)

    fun processUserIntentsAndObserveUiStates(
        loginIntents: Flow<LoginUIntent>,
        coroutineScope: CoroutineScope = viewModelScope,
    ) {
        loginIntents.buffer()
            .flatMapMerge { loginIntent ->
                processor.actionProcessor(loginIntent.toAction())
            }
            .scan(loginDefaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduceWith result }
            }
            .onEach {
                uiState.value = it
            }
            .launchIn(coroutineScope)
    }

    private fun LoginUIntent.toAction(): LoginAction {
        return when (this) {
            is PressingBtnGetInto -> GetSingWhitEmailAndPassword(this.user, this.password)
        }
    }

}