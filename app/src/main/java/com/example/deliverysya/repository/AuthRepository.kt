package com.example.deliverysya.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor() {
    private var auth = FirebaseAuth.getInstance()

    fun loginEmailPass(email: String, password: String): Flow<FirebaseUser> = flow{
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit((result.user?.let { it }!!))
        }catch (e:Exception){}
    }
}
