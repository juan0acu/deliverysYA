package com.example.deliverysya.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliverysya.R
import com.example.deliverysya.presentation.navigation.AppScreen
import com.example.uicomponents.TitleText
import kotlinx.coroutines.delay
import kotlin.time.times

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {
    val timeMillis : Long = 3000
Column() {
    LaunchedEffect(key1 = true) {
        delay(timeMillis)
        navController.popBackStack()
        navController.navigate(AppScreen.LoginScreen.route)
    }
    SplashScreenInitialApp(
        modifier = modifier
    )
}

}

@Composable
fun SplashScreenInitialApp(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()

    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.dise_logo_500dp),
                contentDescription = stringResource(id = R.string.logo_name),
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.dc_padding_100))
                    .width(dimensionResource(id = R.dimen.dc_padding_100))
            )
        }
        TitleText(stringResource(id = R.string.app_name))
    }
}

