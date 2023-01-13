package com.example.deliverysya.ui.navigation

sealed class AppScreen (val route: String){
    object SplashScreen: AppScreen("splash_screen")
    object LoginScreen: AppScreen("login_screen")
    object IntroductionRiders: AppScreen("introduction_riders")

}