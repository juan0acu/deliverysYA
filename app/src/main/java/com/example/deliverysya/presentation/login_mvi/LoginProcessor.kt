package com.example.deliverysya.presentation.login_mvi

import androidx.core.util.PatternsCompat
import com.example.deliverysya.data.DataRepository
import com.example.deliverysya.presentation.login_mvi.LoginResult.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginProcessor @Inject constructor(private val dataRepository: DataRepository) {

    fun actionProcessor(actions: LoginAction): Flow<LoginResult> =
        when (actions) {
            is LoginAction.GetSingWhitEmailAndPasswordAction -> singWhitEmailAndPasswordProcessor(
                actions.user,
                actions.Password
            )
        }

    private fun singWhitEmailAndPasswordProcessor(user: String, pass: String) =
        flow<GetSingWhitEmailAndPasswordResult> {
            if (user.isEmpty() && pass.isEmpty()) {
                emit(GetSingWhitEmailAndPasswordResult.EmptyValues(EMPTY_VALUE))
            } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(user).matches()) {
                emit(GetSingWhitEmailAndPasswordResult.Error(ERROR_FORMAT_EMAIL))
            }
            else {
                val remoteLogin = dataRepository.logearWhitEmailAndPass(user, pass).first()
                println("Resultado $remoteLogin")
                if (remoteLogin?.user != null) {
                    emit(GetSingWhitEmailAndPasswordResult.Success(true))
                } else {
                    emit(GetSingWhitEmailAndPasswordResult.Error(INCORRECT_CREDENTIALS))
                }
            }
        }.onStart {
            emit(GetSingWhitEmailAndPasswordResult.InProgress)
        }.catch {
            println("ACA ERROR FIREBASE" + it.message)
            emit(GetSingWhitEmailAndPasswordResult.Error(it.message ?: ERROR_CONNECTION))
        }.flowOn(Dispatchers.IO)

    companion object {
        const val INCORRECT_CREDENTIALS = "Las credenciales son Incorrectas"
        const val EMPTY_VALUE = "Existen campos vaciós, debes tener los campos Email y Pass llenos para poder ingresar"
        const val ERROR_CONNECTION = "Error: no ha sido posible conectarse, no es computable"
        const val ERROR_FORMAT_EMAIL = "Error: El correo ingresado no tiene el formado correcto"
    }
}

