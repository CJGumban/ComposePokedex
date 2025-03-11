package com.example.composepokedex.ui.composables

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepokedex.R
import com.example.composepokedex.ui.theme.ComposePokedexTheme
import com.example.composepokedex.ui.theme.Medium2
import com.example.composepokedex.ui.theme.Primary
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize().background(color = Medium2)
       /* .graphicsLayer {
            clip = true
            shape = CircleShape
        }*/
    ) {
        Image(painter = rememberDrawablePainter(
            drawable = getDrawable(
                LocalContext.current,
                R.drawable.ic_pokemon_loading_anim
            )
        ), colorFilter = ColorFilter.tint(
            Primary, BlendMode.Lighten
        ), contentDescription = "pokedexLoadingGif",
            contentScale = ContentScale.Crop,
        modifier = Modifier
            .align(Alignment.Center)
            .size(120.dp)
            .graphicsLayer {

            clip = true
            shape = CircleShape
        })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoadingScreenPreview(){
    ComposePokedexTheme {
        LoadingScreen()
    }
}