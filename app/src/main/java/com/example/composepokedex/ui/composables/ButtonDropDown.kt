package com.example.composepokedex.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composepokedex.ui.theme.ComposePokedexTheme

@Composable
fun MediumButton(
    content: @Composable() (() -> Unit)? = null,
    modifier: Modifier? = null
) {
    val buttonModifier = modifier?:Modifier
    Button(
        content = {
            Row {}
        },
        onClick = {
            
        }
        )
}

@Composable
@Preview
fun MediumButtonPreview() {
    ComposePokedexTheme {
        MediumButton()
    }
}