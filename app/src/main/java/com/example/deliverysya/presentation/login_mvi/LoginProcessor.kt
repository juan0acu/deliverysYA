package com.example.deliverysya.presentation.login_mvi

import com.example.deliverysya.data.DataRepository
import com.example.deliverysya.presentation.login_mvi.LoginResult.*
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
            is LoginAction.GetSingWhitEmailAndPasswordAction -> singWhitEmailAndPasswordProcessor(actions.user,actions.Password)
        }

    private fun singWhitEmailAndPasswordProcessor(user: String, pass: String): Flow<GetSingWhitEmailAndPasswordResult> =
        dataRepository.logearWhitEmailAndPass(user,pass)
            .map { remoteLogin ->
                println("Resultado $remoteLogin")
                if(remoteLogin?.user != null){
                    LoginResult.GetSingWhitEmailAndPasswordResult.Success(true)
                }else{
                    GetSingWhitEmailAndPasswordResult.Error(INCORRECT_CREDENTIALS)}
            }
            .onStart {
                emit(GetSingWhitEmailAndPasswordResult.InProgress)
            }
            .catch {
               println("ACA ERROR FIREBASE"+it.message)
                emit(GetSingWhitEmailAndPasswordResult.Error(it.message?: ERROR_CONNECTION))
            }
            .flowOn(Dispatchers.IO)

    companion object {
        const val INCORRECT_CREDENTIALS = "Las credenciales son Incorrectas"
        const val EMPTY_VALUE = "No ha ingresado nada"
        const val ERROR_CONNECTION = "Error: no ha sido posible conectarse, no es computable"
    }
}

