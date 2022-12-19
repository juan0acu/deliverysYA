package com.example.deliverysya.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.ui.screens.loginScreen
import com.example.deliverysya.ui.screens.splashScreen

@Composable
fun appNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController ,
            startDestination = AppScreen.SplashScreen.route)
    {
       composable(AppScreen.SplashScreen.route){
        splashScreen(navController)
       }
        composable(AppScreen.LoginScreen.route){
          loginScreen(navController)
        }git add README.md
    }
}