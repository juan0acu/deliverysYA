package com.example.deliverysya.ui.screens.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.MainActivity
import com.example.deliverysya.R
import com.example.deliverysya.presentation.login.LoginViewModel
import com.example.deliverysya.ui.navigation.AppScreen
import com.example.uicomponents.BodyText
import com.example.uicomponents.RoundedButton
import com.example.uicomponents.TitleText
import com.example.uicomponents.TransparentTextField
import com.example.uicomponents.theme.DeliveryColor


@Composable
fun LoginScreens(
    navController: NavController,
    activity: MainActivity,
) {
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val loginViewModel : LoginViewModel = viewModel()
    val isLoading by loginViewModel.isLoading().observeAsState(false)
    val hasErrors by loginViewModel.hasErrors().observeAsState(false)


    if (isLoading){
        navController.popBackStack()
        navController.navigate(AppScreen.IntroductionRiders.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        LogoBannerSection()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                color = Color.White,
                shape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 8
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    TitleText(
                        title = stringResource(id = R.string.welcome_login)
                    )

                    BodyText(
                        body = stringResource(id = R.string.slogan)                     
                        )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                       TransparentTextField(
                           textFieldValue = emailValue,
                            textLabel = stringResource(id = R.string.ingresar_email),
                            keyboardType = KeyboardType.Email,
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            imeAction = ImeAction.Next
                        )

                        TransparentTextField(
                            textFieldValue = passwordValue,
                            textLabel = stringResource(id = R.string.ingresar_pass),
                            keyboardType = KeyboardType.Password,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    loginViewModel.loginEmailPass(emailValue.value,passwordValue.value,activity)

                                }
                            ),
                            imeAction = ImeAction.Done,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        passwordVisibility = !passwordVisibility
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (passwordVisibility) {
                                            Icons.Default.Visibility
                                        } else {
                                            Icons.Default.VisibilityOff
                                        },
                                        contentDescription = stringResource(id = R.string.alternar_icon)
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisibility) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.olvidaste_pass),
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.End
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        RoundedButton(
                            text = stringResource(id = R.string.ingresar_login),
                          // validate = false,
                            onClick = {
                               loginViewModel.validateEmail(emailValue.value,activity)
                                loginViewModel.validatePassword(passwordValue.value,activity)
                                if(hasErrors){
                                    loginViewModel.loginEmailPass(emailValue.value,passwordValue.value,activity)
                                }
                            }
                        )
                        RegisterAccount()
                    }
                }
            }

        }
    }
}

@Composable
fun LogoBannerSection() {
    Image(
        painter = painterResource(id = R.drawable.delv_log_500dp),
        contentDescription = stringResource(id = R.string.logo_name),
        contentScale = ContentScale.Inside
    )

}

/*@Composable
fun EnterForm(activity:Activity) {
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val loginViewModel : LoginViewModel = viewModel()

    TransparentTextField(
        textFieldValue = emailValue,
        textLabel = stringResource(id = R.string.ingresar_email),
        keyboardType = KeyboardType.Email,
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        imeAction = ImeAction.Next
    )

    TransparentTextField(
        textFieldValue = passwordValue,
        textLabel = stringResource(id = R.string.ingresar_pass),
        keyboardType = KeyboardType.Password,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                loginViewModel.loginEmailPass(emailValue.value,passwordValue.value,activity)

            }
        ),
        imeAction = ImeAction.Done,
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                }
            ) {
                Icon(
                    imageVector = if (passwordVisibility) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = stringResource(id = R.string.alternar_icon)
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}*/

@Composable
fun RegisterAccount(){

    ClickableText(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.no_cuenta))

            withStyle(
                style = SpanStyle(
                    color = DeliveryColor,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(id = R.string.registrar_cuenta))
            }
        }
    ) {
        // TODO("TO REGISTER SCREEN")
    }
}


@Preview
@Composable
fun LoginScreen2Preview() {
    val navController = rememberNavController()
    val activity = MainActivity()
    MaterialTheme() {
        LoginScreens(navController = navController, activity )
    }
}

