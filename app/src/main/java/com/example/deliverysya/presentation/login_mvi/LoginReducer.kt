package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.presentation.login_mvi.LoginUiState.DefaultUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.SuccessUiState
import javax.inject.Inject

internal class LoginReducer @Inject constructor() {

    infix fun LoginUiState.reduceWith(result: LoginResult): LoginUiState{
        return when(val previousState = this){
            is DefaultUiState -> previousState reduceWith result
            is SuccessUiState -> previousState reduceWith result
        }
    }

    private infix fun DefaultUiState.reduceWith(result: LoginResult) = when (result) {
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> this
        is LoginResult.GetSingWhitEmailAndPasswordResult.Success -> SuccessUiState
        is LoginResult.GetSingWhitEmailAndPasswordResult.Error -> DefaultUiState
        is LoginResult.GetSingWhitEmailAndPasswordResult.EmptyValues -> DefaultUiState
        is LoginResult.GetSingWhitEmailAndPasswordResult.IncorrectCredentials-> DefaultUiState
        else -> throw unsupportedReduceCase()
    }

    private infix fun SuccessUiState.reduceWith(result: LoginResult):LoginUiState {
       throw unsupportedReduceCase()
    }




    private fun unsupportedReduceCase() = RuntimeException()

}