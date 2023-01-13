package com.example.uicomponents

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.uicomponents.theme.DeliverysYaTheme

@Composable
fun BodyText(body: String) {
    Text(body, style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Justify)
}

@Preview(
    showBackground = true
)
@Composable
fun BodyTextPreview() {
    DeliverysYaTheme {
        BodyText(stringResource(id = R.string.lorem_ipsum))
    }

}