package com.example.uicomponents.model

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

data class TransparentTextFieldAttrs (
    val modifier: Modifier = Modifier,
    val textFieldValue: MutableState<String>,
    val textLabel: String,
    val maxChar: Int? = null,
    val  capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    val  keyboardType: KeyboardType,
    val  keyboardActions: KeyboardActions,
    val imeAction: ImeAction,
    val  trailingIcon: @Composable() (() -> Unit)? = null,
    val visualTransformation: VisualTransformation = VisualTransformation.None
)