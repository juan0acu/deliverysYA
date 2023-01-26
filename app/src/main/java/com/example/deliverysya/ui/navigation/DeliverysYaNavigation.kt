package com.example.deliverysya.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.ui.screens.SplashScreen
import com.example.deliverysya.ui.screens.login.LoginScreens
import com.example.deliverysya.MainActivity
import com.example.deliverysya.presentation.login_mvi.LoginViewModelMvi
import com.example.deliverysya.ui.navigation.AppScreen.*
import com.example.deliverysya.ui.screens.introduction_riders.IntroductionRiders
import com.example.deliverysya.ui.screens.login.user_login_mvi.LoginScreensMvi
import com.example.deliverysya.ui.screens.user_register.UserRegister


@Composable
internal fun DeliverysYaNavigation(activity: MainActivity) {
    val navController = rememberNavController()
    val loginViewModelMvi: LoginViewModelMvi = viewModel()
    NavHost(
        navController = navController,
        startDestination = SplashScreen.route
    )
    {
        composable(SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(LoginScreen.route) {
            LoginScreens(navController = navController,activity)
        }
        composable(LoginScreenMvi.route) {
            LoginScreensMvi(loginViewModelMvi , navController = navController)
        }
        composable(IntroductionRiders.route){
            IntroductionRiders(navController = navController)
        }
        composable(UserRegister.route){
            UserRegister(navController = navController,activity)
        }
    }
}
