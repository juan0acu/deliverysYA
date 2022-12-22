package com.example.deliverysya.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.uicomponents.BodyText
import com.example.uicomponents.CustomButton
import com.example.uicomponents.CustomTextField
import com.example.uicomponents.TitleText

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {},
        content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.dc_padding_25)),
            horizontalAlignment = Alignment.CenterHorizontally,
           // verticalArrangement = Arrangement.SpaceEvenly
            ) {
            Image(
                painter = painterResource(id = R.drawable.dise_logo_500dp),
                contentDescription = stringResource(id = R.string.logo_name),
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.dc_padding_130))
                    .width(dimensionResource(id = R.dimen.dc_padding_130))
                    //.padding(top = dimensionResource(id = R.dimen.dc_padding_25))
            )
            TitleText(title = stringResource(id = R.string.welcome_login))
            BodyText(stringResource(id = R.string.slogan) )
            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dc_padding_25))) {
                CustomTextField(value = email, placeholder = stringResource(id = R.string.ingresar_email),keyboardType = KeyboardType.Email){
                    email = it
                }
                CustomTextField(value = password, placeholder = stringResource(id = R.string.ingresar_pass), keyboardType = KeyboardType.Password){
                    password = it
                }
            }
            Box(modifier = Modifier .width(150.dp)) {
                CustomButton(stringResource(id = R.string.ingresar_login)) {
                    //Validar si los campos estan vacios o llenos
                    message ="Tu pedido a sido procesado :D"
                }
            }

        }
        }
    )
}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme() {
        LoginScreen(navController)
    }

}