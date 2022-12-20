package com.example.deliverysya.ui.componets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.deliverysya.ui.theme.DeliverysYaTheme

@Composable
fun TitleText(title: String) {
    Text(title, style = MaterialTheme.typography.h3)
}

@Preview(
    showBackground = true
)
@Composable
fun TitleTextPreview() {
    DeliverysYaTheme {
        TitleText("Title")
    }

}