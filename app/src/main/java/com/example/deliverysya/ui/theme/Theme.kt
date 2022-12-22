package com.example.uicomponents.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DeliveryColor,
    primaryVariant = DeliveryColor,
    secondary = secundaryColor
)

private val LightColorPalette = lightColors(
    primary = DeliveryColor,
    primaryVariant = secundaryColor,
    secondary = DeliveryColor

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DeliverysYaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val type = if (darkTheme) {
        com.example.deliverysya.ui.theme.TypographyDark
    } else {
        com.example.deliverysya.ui.theme.Typography
    }

    MaterialTheme(
        colors = colors,
        typography = type,
        shapes = Shapes,
        content = content
    )
}