package com.example.deliverysya.presentation.login

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class UserRegisterViewModel:ViewModel(){
    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)
    fun isLoading(): LiveData<Boolean> = isLoading
    fun hasErrors(): LiveData<Boolean> = hasErrors


    fun validateName(name: String, activity: Activity) {
        return if (name.isEmpty()) {
            Log.d(ContentValues.TAG, "Field can not be empty")
            Toast.makeText(activity, "Campo Nombre y Apellido esta vacío", Toast.LENGTH_SHORT).show()
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

    fun validatePasswordCheck(password: String,password2:String, activity: Activity) {

        return if (password !=password2) {
            Toast.makeText(activity, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            hasErrors.value = false
        } else {
            isLoading.value = true
        }

    }

}