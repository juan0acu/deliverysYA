package com.example.deliverysya.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Login()
}

@Composable
fun Login() {
    Text("Login")
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Login()
}