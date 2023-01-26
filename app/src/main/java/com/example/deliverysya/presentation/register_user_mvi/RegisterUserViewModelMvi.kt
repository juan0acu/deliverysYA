package com.example.deliverysya.presentation.register_user_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliverysya.presentation.login_mvi.LoginResult
import com.example.deliverysya.presentation.login_mvi.LoginUiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
internal class RegisterUserViewModelMvi @Inject constructor(
    private val processor: RegisterUserProcesor,
    private val reducer: RegisterUserReducer
):ViewModel()
{
    fun uiState(): StateFlow<RegisterUserUiState> = uiState
    val registerDefaulUiState: RegisterUserUiState = RegisterUserUiState.DefaultUiState
    private val uiState: MutableStateFlow<RegisterUserUiState> = MutableStateFlow(registerDefaulUiState)
    fun uiEffect(): SharedFlow<RegisterUiEffect> = uiEffect.asSharedFlow()
    private val uiEffect: MutableSharedFlow<RegisterUiEffect> = MutableSharedFlow()

    fun processUserIntentsAndObserveUiStates(
        RegisterUserUItent: Flow<RegisterUserUItent>,
        coroutineScope: CoroutineScope = viewModelScope,
    ) {
        RegisterUserUItent.buffer()
            .flatMapMerge { registerIntent ->
                processor.actionProcessor(registerIntent.toAction( ))
            }
            .handleEffect()
            .scan(registerDefaulUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduceWith result }
            }
            .onEach {
                uiState.value = it
            }
            .launchIn(coroutineScope)
    }

    private fun RegisterUserUItent.toAction(): RegisterUserAction {
        return when (this) {
            is RegisterUserUItent.PressingBtnRegisterUser -> RegisterUserAction.RegisterWhitEmailAndPassAction(
                this.email,
                this.pass,
                this.pass2,
                this.name
            )

        }
    }

    private fun Flow<RegisterUserResult>.handleEffect(): Flow<RegisterUserResult> =
        onEach { result ->
            when (result) {
                is RegisterUserResult.RegisterWhitEmailAndPassResult.EmptyValues -> uiEffect.emit(RegisterUiEffect.ErrorUiEffect(result.emptyvalue))
                is RegisterUserResult.RegisterWhitEmailAndPassResult.Error -> {
                    println("Paso por el Error UIEFfect: ${result.error}")
                    uiEffect.emit(RegisterUiEffect.ErrorUiEffect(result.error))}
                is RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress -> uiEffect.emit(RegisterUiEffect.LoadingUiEffect)
                else -> return@onEach
            }
        }

}