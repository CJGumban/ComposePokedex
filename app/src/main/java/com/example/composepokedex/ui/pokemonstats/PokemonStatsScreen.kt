package com.example.composepokedex.ui.pokemonstats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.composepokedex.R
import com.example.composepokedex.ui.theme.ComposePokedexTheme
import ph.theorangeco.data.models.states.UiState

@Composable
fun PokemonStatsScreen(
    onBackClick:()-> Unit = {},
    onPrevClick:()-> Unit = {},
    onNextClick:()-> Unit = {},
    pokemon: UiState = UiState.Initial
){

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick =onBackClick,
                    modifier = Modifier,
                    enabled = true,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back_arrow),
                        contentDescription = "back icon"
                    )
                }
                IconButton(
                    onClick =onBackClick,
                    modifier = Modifier,
                    enabled = true,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_fav),
                        contentDescription = "back icon"
                    )
                }
            }
        },
        containerColor = Color.White
    ) {paddingValues->
        Box(
            modifier = Modifier.fillMaxSize(1f)
                .background(Color.Black)
                .zIndex(2f)
        ){

        }
        Column(Modifier.padding(paddingValues)
            .fillMaxSize()
            .background(Color.White)){}
    }
}

@Composable
@Preview
fun PokemonStatsScreenPreview(){
    ComposePokedexTheme {
        PokemonStatsScreen()
    }
}

