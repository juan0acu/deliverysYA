package com.example.deliverysya.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.deliverysya.presentation.navigation.AppScreen
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavController) {

    LaunchedEffect(key1 = true){
        delay(3000)
        navController.popBackStack()
        navController.navigate(AppScreen.LoginScreen.route)
    }
    splash()
}

@Composable
fun splash() {
    Text("Splash")
}

@Preview(showBackground = true)
@Composable
fun splashScreenPreview() {
    splash()
}