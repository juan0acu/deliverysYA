package com.example.deliverysya.presentation.register_user_mvi

import android.content.ContentValues
import android.util.Log
import androidx.core.util.PatternsCompat
import com.example.deliverysya.data.RegisterUserRepository
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
internal class RegisterUserProcesor @Inject constructor(private val registerUserRepository: RegisterUserRepository) {

    fun actionProcessor(actions: RegisterUserAction): Flow<RegisterUserResult> = when (actions) {
        is RegisterUserAction.RegisterWhitEmailAndPassAction -> registerUserWithEmailAndPassProcessor(
            actions.email,
            actions.pass,
            actions.pass2,
            actions.name
        )
    }

    private fun registerUserWithEmailAndPassProcessor(email: String, pass: String,pass2: String,name:String) =
        flow {
            if (email.isEmpty() && pass.isEmpty() && pass2.isEmpty() && name.isEmpty()) {
                emit(RegisterUserResult.RegisterWhitEmailAndPassResult.EmptyValues(EMPTY_VALUE))
            } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
                emit(RegisterUserResult.RegisterWhitEmailAndPassResult.Error(ERROR_FORMAT_EMAIL))
            }else if (pass != pass2){
                emit(RegisterUserResult.RegisterWhitEmailAndPassResult.Error(
                    ERROR_CREDENTIAL_UNEQUAL))
            }else {
                val remoteRegister = registerUserRepository.registerUserWhitEmailAndPass(email,pass).first()
                if (remoteRegister?.user != null) {
                    emit(RegisterUserResult.RegisterWhitEmailAndPassResult.Success(true))
                } else {
                    emit(RegisterUserResult.RegisterWhitEmailAndPassResult.Error(ERROR_CONNECTION))
                }
            }
        }.onStart {
            emit(RegisterUserResult.RegisterWhitEmailAndPassResult.InProgress)
        }.catch {
            emit(RegisterUserResult.RegisterWhitEmailAndPassResult.Error(it.message ?: ERROR_CONNECTION))
        }.flowOn(Dispatchers.IO)


    companion object {
        private const val EMPTY_VALUE =
            "Existen campos vaciós, debes tener los campos Nombre,Email,Pass y confirmación llenos para poder registrarse"
        private const val ERROR_CONNECTION =
            "Error: no ha sido posible conectarse, no es computable"
        private const val ERROR_FORMAT_EMAIL =
            "Error: El correo ingresado no tiene el formado correcto"
        private const val ERROR_CREDENTIAL_UNEQUAL =
            "Error: Las contraseñas ingresadas no son iguales"
    }
}