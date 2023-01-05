package com.example.deliverysya.presentation.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliverysya.UserState
import com.example.deliverysya.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)
    private val userValue = MutableLiveData(UserState())
     //val stateFlow = MutableStateFlow(UserState())

    //private val timeMillis: Long = 5000
    fun isLoading(): LiveData<Boolean> = isLoading
    fun hasErrors(): LiveData<Boolean> = hasErrors
    fun userValue(): LiveData<UserState> = userValue

    fun loginEmailPassRepository(email: String,password: String){
        authRepository.loginEmailPass(email,password).onEach {
            if (it == null){

            }else{
                userValue.value?.user = it
            }
        }.launchIn(viewModelScope)
    }

    fun loginEmailPass(email: String, password: String, activity: Activity) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d(
                        TAG,
                        "Inicio de sesión con correo electrónico exitoso"
                    )
                    //val user = auth.currentUser
                    isLoading.value = true
                } else {
                    Log.w(
                        TAG,
                        "Error en el inicio de sesión correo o clave errónea",
                        task.exception
                    )
                    Toast.makeText(
                        activity, "La autenticación falló.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun validateEmail(email: String, activity: Activity) {
        return if (email.isEmpty()) {
            Log.d(TAG, "Field can not be empty")
            Toast.makeText(activity, "Campo correo vacío", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.d(TAG, "Please enter a valid email address")
            Toast.makeText(activity, "Email no valido", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else {
            hasErrors.value = true
        }
    }

    fun validatePassword(password: String, activity: Activity) {
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" + //Al menos un dígito
                    ".{6,}" +  //Al menos 8 caracteres
                    "$"

        )
        return if (password.isEmpty()) {
            Toast.makeText(activity, "Campo contraseña vacío", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else if (!passwordRegex.matcher(password).matches()) {
            Toast.makeText(
                activity,
                "La contraseña es demasiado débil, mínimo 6 caracteres",
                Toast.LENGTH_SHORT
            ).show()
            hasErrors.value = false
        } else {
            hasErrors.value = true
        }

    }

}
