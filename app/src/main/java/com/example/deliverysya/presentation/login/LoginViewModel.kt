package com.example.deliverysya.presentation.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)


    //private val timeMillis: Long = 5000
    fun isLoading(): LiveData<Boolean> = isLoading
    fun hasErrors(): LiveData<Boolean> = hasErrors

/*Aun no se trabajara con Login de Google
    fun loginWhithGoogle(activity: Activity) {
        isLoading.postValue(true)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, gso);

        val signInIntent: Intent = client.signInIntent
        activity.startActivityForResult(signInIntent, 1)

    }*/

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
