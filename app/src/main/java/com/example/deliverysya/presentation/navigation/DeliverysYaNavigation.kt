package com.example.deliverysya.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.ui.screens.login.LoginScreen
import com.example.deliverysya.ui.screens.SplashScreen
import com.example.deliverysya.ui.screens.login.LoginScreenss


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
            LoginScreenss(navController = navController)
        }
    }
}