package com.example.deliverysya.data

import com.example.deliverysya.data.network.AuthenticationService
import com.example.deliverysya.data.remote.source.RemoteLogin
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val Authenticacion: AuthenticationService) {
    fun logearWhitEmailAndPass(user:String, pass:String) = flow {
        val response = Authenticacion.loginWhitEmailAndPass(user,pass)
        emit(response as RemoteLogin)
    }
}