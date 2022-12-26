package com.example.deliverysya.ui.navigation

sealed class AppScreen (val route: String){
    object SplashScreen: AppScreen("splash_screen")
    object LoginScreen: AppScreen("login_screen")
    object LoginScreen2: AppScreen("login_screen2")
}