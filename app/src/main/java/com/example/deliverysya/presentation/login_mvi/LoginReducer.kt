package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.presentation.login_mvi.LoginUiState.DefaultUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.LoadingUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.SuccessUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.ErrorUiState
import javax.inject.Inject

internal class LoginReducer @Inject constructor() {

    infix fun LoginUiState.reduceWith(result: LoginResult): LoginUiState{
        return when(val previousState = this){
            is DefaultUiState -> previousState reduceWith result
            is LoadingUiState -> previousState reduceWith result
            is SuccessUiState -> previousState reduceWith result
            is ErrorUiState -> previousState reduceWith result
        }
    }

    private infix fun LoadingUiState.reduceWith(result: LoginResult) = when (result){
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> LoadingUiState
        is LoginResult.GetSingWhitEmailAndPasswordResult.Success -> SuccessUiState
        is LoginResult.GetSingWhitEmailAndPasswordResult.Error -> ErrorUiState(result.error)
        is LoginResult.GetSingWhitEmailAndPasswordResult.EmptyValues -> ErrorUiState(result.emptyvalue)
        is LoginResult.GetSingWhitEmailAndPasswordResult.IncorrectCredentials-> ErrorUiState(result.message)
    }

    private infix fun DefaultUiState.reduceWith(result: LoginResult) = when (result) {
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private infix fun SuccessUiState.reduceWith(result: LoginResult) = when (result) {
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private infix fun ErrorUiState.reduceWith(result: LoginResult) = when (result) {
        is LoginResult.GetSingWhitEmailAndPasswordResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private fun unsupportedReduceCase() = RuntimeException()

}