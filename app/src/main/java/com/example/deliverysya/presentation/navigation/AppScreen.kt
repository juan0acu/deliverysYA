package com.example.deliverysya.presentation.navigation

sealed class AppScreen (val route: String){
    object SplashScreen: AppScreen("splash_screen")
    object LoginScreen: AppScreen("login_screen")
}