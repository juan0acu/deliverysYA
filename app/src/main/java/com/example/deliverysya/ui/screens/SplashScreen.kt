package com.example.deliverysya.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliverysya.R
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
    Column(horizontalAlignment = Alignment.CenterHorizontally
        , modifier = Modifier.padding(16.dp)) {
        Image(painter = painterResource(id = R.drawable.logo_deliverysya),
            contentDescription ="Logo" , modifier = Modifier.size(400.dp,400.dp))
    }
    Text("Splash")
}

@Preview(showBackground = true)
@Composable
fun splashScreenPreview() {
    splash()
}