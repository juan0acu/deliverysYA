package com.example.deliverysya.ui.navigation

sealed class AppScreen (val route: String){
    object SplashScreen: AppScreen("splash_screen")
    object LoginScreen: AppScreen("login_screen")
    object LoginScreenMvi: AppScreen("login_screen_mvi")
    object IntroductionRiders: AppScreen("introduction_riders")
    object UserRegister: AppScreen("user_register")

}