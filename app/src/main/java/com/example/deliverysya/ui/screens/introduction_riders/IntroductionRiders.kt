package com.example.deliverysya.ui.screens.introduction_riders

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.R
import com.example.uicomponents.TitleText

@Composable
fun IntroductionRiders(navController: NavController) {
    TitleText(stringResource(id = R.string.title))
}

@Preview
@Composable
fun IntroductionRidersPreview() {
    val navController = rememberNavController()

    IntroductionRiders(navController)
}