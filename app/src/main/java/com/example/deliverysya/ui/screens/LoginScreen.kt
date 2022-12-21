package com.example.deliverysya.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.deliverysya.R

@Composable
fun LoginScreen(navController: NavController) {
    Login()
}

@Composable
fun Login() {
    Text(stringResource(id = R.string.welcome_login))
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Login()
}