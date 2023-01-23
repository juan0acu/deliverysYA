package com.example.deliverysya.presentation.login

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class UserRegisterViewModel : ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)
    fun isLoading(): LiveData<Boolean> = isLoading
    fun hasErrors(): LiveData<Boolean> = hasErrors


    fun validateName(name: String, activity: Activity) {
        return if (name.isEmpty()) {
            Log.d(ContentValues.TAG, "Field can not be empty")
            Toast.makeText(activity, "Campo Nombre y Apellido esta vacío", Toast.LENGTH_SHORT)
                .show()
            hasErrors.value = false
        } else {
            hasErrors.value = true
        }
    }

    fun validateEmail(email: String, activity: Activity) {
        return if (email.isEmpty()) {
            Log.d(ContentValues.TAG, "Field can not be empty")
            Toast.makeText(activity, "Campo correo vacío", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.d(ContentValues.TAG, "Please enter a valid email address")
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

    fun validatePassword2(password: String, activity: Activity) {
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

    fun validatePasswordCheck(
        password: String,
        password2: String,
        activity: Activity,
        name: String,
        email: String
    ) {

        return if (password != password2) {
            Toast.makeText(activity, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else {
            userRegisterFirebase(name, email, password, activity)
        }

    }

    private fun userRegisterFirebase(
        name: String,
        email: String,
        password: String,
        activity: Activity
    ) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "Usuario creado de forma exitosa:success")
                    // val user = auth.currentUser
                    isLoading.value = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "La creación de usuario a fallado", task.exception)
                    Toast.makeText(
                        activity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

    }

}