package com.example.deliverysya.ui.screens.introduction_riders

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.deliverysya.R
import com.example.uicomponents.TitleText

@Composable
fun IntroductionRiders() {
    TitleText(stringResource(id = R.string.title))
}

@Preview
@Composable
fun IntroductionRidersPreview() {
    IntroductionRiders()
}