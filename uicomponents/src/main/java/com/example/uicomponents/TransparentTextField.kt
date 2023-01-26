package com.example.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.uicomponents.model.TransparentTextFieldAttrs

@Composable
fun TransparentTextField(
    transparentTextFieldAttrs: TransparentTextFieldAttrs
) {
    TextField(
        modifier =transparentTextFieldAttrs.modifier.fillMaxWidth() ,
        value = transparentTextFieldAttrs.textFieldValue.value.take(transparentTextFieldAttrs.maxChar ?: 40),
        onValueChange = {transparentTextFieldAttrs.textFieldValue.value = it },
        label = {
            Text(text = transparentTextFieldAttrs.textLabel)
        },
        trailingIcon = transparentTextFieldAttrs.trailingIcon,
        keyboardOptions = KeyboardOptions(
            capitalization = transparentTextFieldAttrs.capitalization,
            keyboardType = transparentTextFieldAttrs.keyboardType,
            imeAction = transparentTextFieldAttrs.imeAction
        ),
        keyboardActions = transparentTextFieldAttrs.keyboardActions,
        visualTransformation = transparentTextFieldAttrs.visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )

    )
}