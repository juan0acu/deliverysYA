package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.data.DataRepository
import com.example.deliverysya.presentation.login_mvi.LoginResult.*
import com.example.deliverysya.presentation.login_mvi.LoginResult.GetSingWhitEmailAndPasswordResult.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginProcessor @Inject constructor(private val dataRepository: DataRepository){

    fun actionProcessor(actions: LoginAction): Flow<LoginResult> =
        when (actions) {
            is LoginAction.GetSingWhitEmailAndPassword -> SingWhitEmailAndPassword(actions.user,actions.Password)
        }

    private fun SingWhitEmailAndPassword(user: String, pass: String): Flow<LoginResult> =
        dataRepository.logearWhitEmailAndPass(user,pass)
            .map { remoteLogin ->
                if(user.isEmpty() && pass.isEmpty()) {
                    EmptyValues(EMPTY_VALUE)
                }else if (remoteLogin.user != user && remoteLogin.password != pass) {
                    IncorrectCredentials(INCORRECT_CREDENTIALS)
                }else {
                    Success(remoteLogin)
                }
            }
            .onStart {
                emit(InProgress)
            }
            .catch {
                emit(GetSingWhitEmailAndPasswordResult.Error(ERROR_CONNECTION))
            }
            .flowOn(Dispatchers.IO)

    companion object {
        const val INCORRECT_CREDENTIALS = "Las credenciales son Incorrectas"
        const val EMPTY_VALUE = "No ha ingresado nada"
        const val ERROR_CONNECTION = "Error: no ha sido posible conectarse, no es computable"
    }
}

