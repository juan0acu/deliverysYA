package com.example.deliverysya.presentation.register_user_mvi

import com.example.deliverysya.presentation.register_user_mvi.RegisterUserUiState.*
import javax.inject.Inject


internal class RegisterUserReducer @Inject constructor() {

    infix fun RegisterUserUiState.reduceWith(result: RegisterUserResult): RegisterUserUiState {
        return when(val previousState = this){
            is DefaultUiState -> previousState reduceWith result
            is LoadingUiState -> previousState reduceWith result
            is SuccessUiState -> previousState reduceWith result
            is ErrorUiState -> previousState reduceWith result
        }
    }

    private infix fun LoadingUiState.reduceWith(result: RegisterUserResult)= when (result){
        is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> LoadingUiState
        is RegisterUserResult.RegisterWhitEmailAndPassResult.Success -> SuccessUiState
        is RegisterUserResult.RegisterWhitEmailAndPassResult.Error -> ErrorUiState(result.error)
        is RegisterUserResult.RegisterWhitEmailAndPassResult.EmptyValues -> ErrorUiState(result.emptyvalue)
    }

    private infix fun DefaultUiState.reduceWith(result: RegisterUserResult) = when (result){
        is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private infix fun SuccessUiState.reduceWith(result: RegisterUserResult) = when (result){
        is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()

    }

    private infix fun ErrorUiState.reduceWith(result: RegisterUserResult) = when (result){
        is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> LoadingUiState
        else -> throw unsupportedReduceCase()
    }

    private fun unsupportedReduceCase() = RuntimeException()

}