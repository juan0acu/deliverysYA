package com.example.deliverysya.data.network

import com.example.deliverysya.presentation.login_mvi.LoginResult
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {


    suspend fun loginWhitEmailAndPass(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()



    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.GetSingWhitEmailAndPasswordResult.IncorrectCredentials("Error en el inicio de sesión correo o clave errónea")
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResult.GetSingWhitEmailAndPasswordResult.Success(result.user?.isEmailVerified ?: false)
        }
    }

}