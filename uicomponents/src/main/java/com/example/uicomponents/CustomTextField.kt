package com.example.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.uicomponents.model.CustomTextFieldAttrs


@Composable
fun CustomTextField(
    customTextFieldAttrs: CustomTextFieldAttrs
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = customTextFieldAttrs.value,
        onValueChange = customTextFieldAttrs.onValueChange,
        textStyle = TextStyle(color = Color.Black),
        label = {
            Text(customTextFieldAttrs.placeholder, style = MaterialTheme.typography.caption)
        },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                if (customTextFieldAttrs.onGlobalPositioned != null) {
                    customTextFieldAttrs.onGlobalPositioned?.let { it(coordinates) }
                }

            },
        enabled = customTextFieldAttrs.enabled,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = customTextFieldAttrs.keyboardType
        ),
        trailingIcon = customTextFieldAttrs.trailingIcon

    )
}

