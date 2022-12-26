package com.example.deliverysya.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.ui.screens.login.LoginScreen
import com.example.deliverysya.ui.screens.SplashScreen
import com.example.deliverysya.ui.screens.login.LoginScreenss
import com.example.deliverysya.presentation.login.LoginViewModel
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.deliverysya.presentation.login.LoginViewModelFactory


@Composable
fun DeliverysYaNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route
    )
    {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreen.LoginScreen2.route) {
            val viewModel : LoginViewModel = viewModel()

//            val viewModel: LoginViewModel by viewModels()
            val isLoading by viewModel.isLoading().observeAsState(false)
            LoginScreenss(navController = navController, isLoading) {
                viewModel.loginWhithGoogle()
            }
        }
    }
}