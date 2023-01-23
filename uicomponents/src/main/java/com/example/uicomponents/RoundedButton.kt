package com.example.uicomponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uicomponents.theme.DeliveryColor

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    validate: Boolean = false,
    onClick: () -> Unit
) {

        Button(
            modifier = modifier
                .width(280.dp)
                .height(50.dp),
            onClick = onClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DeliveryColor
            )
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h6.copy(
                    color = Color.White
                )
            )
        }
}