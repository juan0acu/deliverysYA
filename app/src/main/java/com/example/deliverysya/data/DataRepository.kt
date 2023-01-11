package com.example.deliverysya.data

import com.example.deliverysya.data.network.AuthenticationService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val authenticationService: AuthenticationService) {
    fun logearWhitEmailAndPass(user:String, pass:String) = flow {
        val response = authenticationService.loginWhitEmailAndPass(user,pass)
        emit(response)
    }
}