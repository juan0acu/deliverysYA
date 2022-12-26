package com.example.deliverysya.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){
private val isLoading = MutableLiveData(false)
    val timeMillis : Long = 5000
    fun isLoading() : LiveData<Boolean> = isLoading

    fun loginWhithGoogle(){
        isLoading.postValue(true)
        viewModelScope.launch{
            delay(timeMillis)
            isLoading.postValue(false)
        }
    }
}
/*

class LoginViewModelFactory() : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}*/
