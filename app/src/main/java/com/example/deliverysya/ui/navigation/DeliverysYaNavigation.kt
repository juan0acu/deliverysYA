package com.example.deliverysya.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.ui.screens.login.LoginScreen
import com.example.deliverysya.ui.screens.SplashScreen
import com.example.deliverysya.ui.screens.login.LoginScreenss
import com.example.deliverysya.presentation.login.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.deliverysya.MainActivity
import com.example.deliverysya.ui.screens.introduction_riders.IntroductionRiders


@Composable
fun DeliverysYaNavigation(activity: MainActivity) {
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
            LoginScreenss(navController = navController,activity)
        }
        composable(AppScreen.IntroductionRiders.route){
            IntroductionRiders(navController = navController)
        }
    }
}
