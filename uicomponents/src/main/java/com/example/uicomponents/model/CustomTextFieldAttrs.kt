package com.example.uicomponents.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.text.input.KeyboardType

data class CustomTextFieldAttrs(
    val value: String,
    val placeholder: String,
    val enabled: Boolean = true,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val onGlobalPositioned: ((LayoutCoordinates) -> Unit)? = null,
    val onValueChange: (String) -> Unit
)