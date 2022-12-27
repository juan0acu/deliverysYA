package com.example.deliverysya.presentation.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private val isLoading = MutableLiveData(false)
    private val hasErrors = MutableLiveData(false)


    private val timeMillis: Long = 5000
    fun isLoading(): LiveData<Boolean> = isLoading

    fun loginWhithGoogle(activity: Activity) {
        isLoading.postValue(true)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, gso);

        val signInIntent: Intent = client.signInIntent
        activity.startActivityForResult(signInIntent, 1)

    }

    fun loginEmailPass(email: String, password: String,activity: Activity) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    isLoading.value = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        activity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
