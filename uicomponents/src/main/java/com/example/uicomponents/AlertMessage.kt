package com.example.uicomponents

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertMessage(
    title: String,
    message: String,
    showDialogg: MutableState<Boolean>,
    onClose: () -> Unit
) {
    if (showDialogg.value){
        AlertDialog(onDismissRequest = {
            onClose() },
            title = {
                Text(text = title, style = TextStyle(color = Color.Black))
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(onClick = { onClose ()}) {
                    Text(text = stringResource(id = R.string.text_ok))
                }
            }
        )
    }

}

