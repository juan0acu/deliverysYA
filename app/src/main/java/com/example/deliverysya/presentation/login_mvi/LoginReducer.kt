package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.presentation.login_mvi.LoginUiState.DefaultUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.LoadingUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.SuccessUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.ErrorUiState

internal class LoginReducer {
    infix fun LoginUiState.reduceWith(result: LoginResult): LoginUiState{
        return when(val previousState = this){
            is DefaultUiState -> previousState reduceWith result
            is LoadingUiState -> previousState reduceWith result
            is SuccessUiState -> previousState reduceWith result
            is ErrorUiState -> previousState reduceWith result
        }
    }

    private infix fun DefaultUiState.reduceWith(result: LoginResult) = when (result) {
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private fun unsupportedReduceCase() = RuntimeException()

}