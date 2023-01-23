package com.example.uicomponents

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview


typealias navigationAction = () -> Unit
@Composable
fun CustomAppBar(title : String?=null,
                 navigationIcon: ImageVector?=null,
                 navigationAction: navigationAction? = null){
    val titleText = title ?: stringResource(id = R.string.app_name)
    if (navigationIcon != null && navigationAction != null){
        TopAppBar(
            title = { Text(titleText) },
            navigationIcon ={
                IconButton(onClick = {
                    navigationAction() })
                {
                    Icon(navigationIcon,"")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    } else {
        TopAppBar(
            title = { Text(titleText) },
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview(
    showBackground = true)
@Composable
fun CustomAppBarPreview() {
    MaterialTheme {
        CustomAppBar(stringResource(id = R.string.app_name))
    }
}