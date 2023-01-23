package com.example.deliverysya.ui.screens.login.user_login_mvi

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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.deliverysya.R
import com.example.deliverysya.presentation.login_mvi.LoginUiEffect
import com.example.deliverysya.presentation.login_mvi.LoginUiState
import com.example.deliverysya.presentation.login_mvi.LoginUiState.*
import com.example.deliverysya.presentation.login_mvi.LoginViewModelMvi
import com.example.deliverysya.ui.navigation.AppScreen
import com.example.uicomponents.*
import com.example.uicomponents.model.TransparentTextFieldAttrs
import com.example.uicomponents.theme.DeliveryColor
import com.example.uicomponents.theme.DeliverysYaTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow

@FlowPreview
@Composable
internal fun LoginScreensMvi(
    loginViewModelMvi: LoginViewModelMvi, navController: NavController
) {
    val uiEffect = remember { loginViewModelMvi.uiEffect() }
    val loginIntentHandler = LoginIntentHandler().apply {
        coroutineScope = rememberCoroutineScope()
    }
    loginViewModelMvi.processUserIntentsAndObserveUiStates(loginIntentHandler.userIntents())
    val uiState =
        remember { loginViewModelMvi.uiState() }.collectAsState(initial = loginViewModelMvi.loginDefaultUiState)
    LoginContent(loginIntentHandler, uiState, navController, uiEffect)
}

@Composable
private fun LoginContent(
    intentHandler: LoginIntentHandler,
    uiState: State<LoginUiState>,
    navController: NavController,
    uiEffect: SharedFlow<LoginUiEffect>
) {
    when (val value = uiState.value) {
        is DefaultUiState -> {
            Body(navController, intentHandler, uiEffect)
        }
        is SuccessUiState -> {
            navController.popBackStack()
            navController.navigate(AppScreen.IntroductionRiders.route)
        }
    }
}

@Composable
internal fun Body(
    navController: NavController,
    loginIntentHandler: LoginIntentHandler,
    uiEffect: SharedFlow<LoginUiEffect>
) {
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val showLoadingDialog = remember { mutableStateOf(false) }
    val messageError = remember { mutableStateOf("") }

    UiEffectSection(
        uiEffect = uiEffect,
        onDialog = {
            messageError.value = it
        },
        showDialog,
        showLoadingDialog
    )
    ShowDialogg(showDialogg = showDialog, meesage = messageError.value)
    ShowLoadingDialog(openDialog = showLoadingDialog)

    DeliverysYaTheme {
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
                        TitlePrincipal()

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            FormEmailAndPass(emailValue, passwordValue)

                            RecoverPassword()
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            EnterAccount(loginIntentHandler, emailValue.value, passwordValue.value)

                            RegisterAccount(navController)
                        }
                    }
                }

            }
        }
    }

}

@Composable
internal fun UiEffectSection(
    uiEffect: SharedFlow<LoginUiEffect>,
    onDialog: (String) -> Unit,
    showDialog: MutableState<Boolean>,
    showLoadingDialog: MutableState<Boolean>
) {

    LaunchedEffect(uiEffect) {
        uiEffect.collect { loginUiEffect ->
            when (loginUiEffect) {
                is LoginUiEffect.ErrorUiEffect -> {
                    println("Paso por el Launched con el error: ${loginUiEffect.error}")
                    showDialog.value = true
                    showLoadingDialog.value = false
                    onDialog.invoke(loginUiEffect.error)
                }
                LoginUiEffect.LoadingUiEffect -> showLoadingDialog.value = true
            }
        }
    }
}

@Composable
fun ShowLoadingDialog(openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ShowDialogg(showDialogg: MutableState<Boolean>, meesage: String) {
    AlertMessage(title = "Error", message = meesage, showDialogg) {
        showDialogg.value = false
        // Body(navController,intentHandler)
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

@Composable
fun TitlePrincipal() {
    TitleText(
        title = stringResource(id = R.string.welcome_login)
    )

    BodyText(
        body = stringResource(id = R.string.slogan)
    )
}

@Composable
fun FormEmailAndPass(emailValue: MutableState<String>, passwordValue: MutableState<String>) {

    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    TransparentTextField(
        TransparentTextFieldAttrs(
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
    )
    TransparentTextField(
        TransparentTextFieldAttrs(
            textFieldValue = passwordValue,
            textLabel = stringResource(id = R.string.ingresar_pass),
            keyboardType = KeyboardType.Password,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    //Acción de inicio

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
    )

}

@Composable
fun RecoverPassword() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.olvidaste_pass),
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.End
    )
}

@Composable
fun EnterAccount(
    loginIntentHandler: LoginIntentHandler,
    emailValue: String,
    passwordValue: String
) {
    RoundedButton(
        text = stringResource(id = R.string.ingresar_login),
        // validate = false,
        onClick = {
            println("email $emailValue")
            println("pass $passwordValue")
            loginIntentHandler.pressIngBtnGetIntoIntent(emailValue, passwordValue)
        }
    )
}

@Composable
fun RegisterAccount(navController: NavController) {

    ClickableText(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.no_cuenta))
            append("   ")
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
        navController.navigate(AppScreen.RegisterUserMvi.route)
        // TODO("TO REGISTER SCREEN")
    }
}
