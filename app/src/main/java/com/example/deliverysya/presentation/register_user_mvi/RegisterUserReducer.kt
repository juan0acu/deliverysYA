package com.example.deliverysya.presentation.register_user_mvi

import com.example.deliverysya.presentation.register_user_mvi.RegisterUserUiState.*
import javax.inject.Inject


internal class RegisterUserReducer @Inject constructor() {

    infix fun RegisterUserUiState.reduceWith(result: RegisterUserResult): RegisterUserUiState {
        return when(val previousState = this){
            is DefaultUiState -> previousState reduceWith result
            is SuccessUiState -> previousState reduceWith result
        }
    }

    private infix fun DefaultUiState.reduceWith(result: RegisterUserResult) = when (result){
        is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> this
        is RegisterUserResult.RegisterWhitEmailAndPassResult.Success -> SuccessUiState
        is RegisterUserResult.RegisterWhitEmailAndPassResult.Error -> DefaultUiState
        is RegisterUserResult.RegisterWhitEmailAndPassResult.EmptyValues -> DefaultUiState
        else -> throw unsupportedReduceCase()
    }

    private infix fun SuccessUiState.reduceWith(result: RegisterUserResult): RegisterUserUiState {
       throw unsupportedReduceCase()
    }
    private fun unsupportedReduceCase() = RuntimeException()

}