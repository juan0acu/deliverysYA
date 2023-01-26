package com.example.deliverysya.presentation.login_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliverysya.presentation.login_mvi.LoginAction.*
import com.example.deliverysya.presentation.login_mvi.LoginResult.*
import com.example.deliverysya.presentation.login_mvi.LoginResult.GetSingWhitEmailAndPasswordResult.*
import com.example.deliverysya.presentation.login_mvi.LoginUIntent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
internal class LoginViewModelMvi @Inject constructor(
    private val reducer: LoginReducer ,
    private val processor: LoginProcessor
    ) : ViewModel()
{
    fun uiState(): StateFlow<LoginUiState> = uiState
    fun uiEffect():SharedFlow<LoginUiEffect> = uiEffect.asSharedFlow()
    val loginDefaultUiState: LoginUiState = LoginUiState.DefaultUiState
    private val uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(loginDefaultUiState)
    private val uiEffect: MutableSharedFlow<LoginUiEffect> = MutableSharedFlow()

    fun processUserIntentsAndObserveUiStates(
        loginIntents: Flow<LoginUIntent>,
        coroutineScope: CoroutineScope = viewModelScope,
    ) {
        loginIntents.buffer()
            .flatMapMerge { loginIntent ->
                processor.actionProcessor(loginIntent.toAction())
            }
            .handleEffect()
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
            is PressingBtnGetInto -> GetSingWhitEmailAndPasswordAction(this.user, this.password)
        }
    }

   private fun Flow<LoginResult>.handleEffect(): Flow<LoginResult> =
        onEach { result ->
            when (result) {
                is EmptyValues -> uiEffect.emit(LoginUiEffect.ErrorUiEffect(result.emptyvalue))
                is Error -> {
                    println("Paso por el Error UIEFfect: ${result.error}")
                    uiEffect.emit(LoginUiEffect.ErrorUiEffect(result.error))}
                is IncorrectCredentials -> uiEffect.emit(LoginUiEffect.ErrorUiEffect(result.message))
                is InProgress -> uiEffect.emit(LoginUiEffect.LoadingUiEffect)
                else -> return@onEach
            }
        }

}