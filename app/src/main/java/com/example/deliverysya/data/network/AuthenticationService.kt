package com.example.deliverysya.data.network
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun loginWhitEmailAndPass(email: String, password: String): AuthResult? {
        return firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun createAccount(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }
}