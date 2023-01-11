package com.example.deliverysya.ui.screens.user_register

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deliverysya.MainActivity
import com.example.deliverysya.R
import com.example.deliverysya.presentation.login.UserRegisterViewModel
import com.example.deliverysya.ui.navigation.AppScreen
import com.example.uicomponents.BodyText
import com.example.uicomponents.CustomAppBar
import com.example.uicomponents.RoundedButton
import com.example.uicomponents.TitleText
import com.example.uicomponents.TransparentTextField
import com.example.uicomponents.model.TransparentTextFieldAttrs
import com.example.uicomponents.theme.DeliverysYaTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserRegister(navController: NavController,activity: MainActivity) {

    val emailValue = rememberSaveable { mutableStateOf("") }
    val nameValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordCheckValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    val UserRegisterViewModel: UserRegisterViewModel = viewModel()

    val isLoading by UserRegisterViewModel.isLoading().observeAsState(false)
    val hasErrors by UserRegisterViewModel.hasErrors().observeAsState(false)

    if (isLoading) {
        navController.popBackStack()
        navController.navigate(AppScreen.LoginScreen.route)
    }

    DeliverysYaTheme {
        Scaffold(topBar = {
            CustomAppBar(navigationIcon = Icons.Filled.ArrowBack) {
                navController.popBackStack()
                navController.navigate(AppScreen.LoginScreen.route)
            }
        }, content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .height(500.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {

                    LogoBannerSection()

                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                    ) {

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(800.dp),
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStartPercent = 8, topEndPercent = 8
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
                                    body = stringResource(id = R.string.title_register_usuario)
                                )

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .bringIntoViewRequester(bringIntoViewRequester)
                                        .onFocusEvent {
                                            coroutineScope.launch {
                                                bringIntoViewRequester.bringIntoView()
                                            }
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    TransparentTextField(
                                        TransparentTextFieldAttrs(
                                            textFieldValue = nameValue,
                                            textLabel = stringResource(id = R.string.text_name_user),
                                            keyboardType = KeyboardType.Text,
                                            keyboardActions = KeyboardActions(onNext = {
                                                focusManager.moveFocus(FocusDirection.Down)
                                            }),
                                            imeAction = ImeAction.Next
                                        )
                                    )
                                    TransparentTextField(
                                        TransparentTextFieldAttrs(
                                            textFieldValue = emailValue,
                                            textLabel = stringResource(id = R.string.ingresar_email),
                                            keyboardType = KeyboardType.Email,
                                            keyboardActions = KeyboardActions(onNext = {
                                                focusManager.moveFocus(FocusDirection.Down)
                                            }),
                                            imeAction = ImeAction.Next
                                        )
                                    )
                                    TransparentTextField(
                                        TransparentTextFieldAttrs(
                                            textFieldValue = passwordValue,
                                            textLabel = stringResource(id = R.string.ingresar_pass),
                                            keyboardType = KeyboardType.Password,
                                            keyboardActions = KeyboardActions(onDone = {
                                                focusManager.clearFocus()

                                            }),
                                            imeAction = ImeAction.Done,
                                            trailingIcon = {
                                                IconButton(onClick = {
                                                    passwordVisibility = !passwordVisibility
                                                }) {
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
                                    TransparentTextField(
                                        TransparentTextFieldAttrs(
                                            textFieldValue = passwordCheckValue,
                                            textLabel = stringResource(id = R.string.ingresar_pass_check),
                                            keyboardType = KeyboardType.Password,
                                            keyboardActions = KeyboardActions(onDone = {
                                                focusManager.clearFocus()

                                            }),
                                            imeAction = ImeAction.Done,
                                            trailingIcon = {
                                                IconButton(onClick = {
                                                    passwordVisibility = !passwordVisibility
                                                }) {
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

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    RoundedButton(text = stringResource(id = R.string.bot_register_user),
                                        // validate = false,
                                        onClick = {
                                            UserRegisterViewModel.validateName(nameValue.value, activity)
                                            UserRegisterViewModel.validateEmail(emailValue.value, activity)
                                            UserRegisterViewModel.validatePassword(passwordValue.value, activity)
                                            UserRegisterViewModel.validatePassword2(passwordCheckValue.value, activity)
                                            if (hasErrors){
                                                UserRegisterViewModel.validatePasswordCheck(passwordValue.value,passwordCheckValue.value,activity,nameValue.value,emailValue.value)
                                            }
                                        })
                                }
                            }
                        }

                    }
                }
            }
        })
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserRegisterPreview() {
    val navController = rememberNavController()
    val activity = MainActivity()
    UserRegister(navController = navController,activity)
}