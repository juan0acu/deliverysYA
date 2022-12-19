package com.example.deliverysya.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliverysya.R
import com.example.deliverysya.presentation.navigation.AppScreen
import com.example.deliverysya.ui.componets.titleText
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
    Column(modifier = Modifier.padding(top = 130.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(painter = painterResource(id = R.drawable.dise_logo),
            contentDescription ="Logo" )
        titleText("DeliverysYA")
    }
}

@Preview(showBackground = true)
@Composable
fun splashScreenPreview() {
    splash()
}