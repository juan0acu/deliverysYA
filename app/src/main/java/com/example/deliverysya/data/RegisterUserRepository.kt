package com.example.deliverysya.data

import com.example.deliverysya.data.network.AuthenticationService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserRepository @Inject constructor(private val authenticationService: AuthenticationService) {
    fun registerUserWhitEmailAndPass(email: String, pass:String) = flow {
        val response = authenticationService.createAccount(email,pass)
        emit(response)
    }
}