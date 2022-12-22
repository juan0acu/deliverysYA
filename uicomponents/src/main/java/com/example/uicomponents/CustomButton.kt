package com.example.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


typealias buttonAction = ()-> Unit

@Composable
fun CustomButton(label: String, action: buttonAction) {
    val uppercaedLabel = label.uppercase()

    Button(
        onClick = { action() },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )

    ) {
        Text(uppercaedLabel)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CustomButtonPreview() {
    MaterialTheme() {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            CustomButton("Button"){

            }
        }

    }
}