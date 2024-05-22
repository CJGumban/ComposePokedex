package com.example.composepokedex.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composepokedex.R
import com.example.composepokedex.ui.theme.ComposePokedexTheme

@Composable
fun PokemonListScreen(
    navController: NavController? = null

){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Spacer(
                modifier = Modifier.height(20.dp))
            Image(
                painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            SearchBar(
                Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                "search...",
                onSearch = { }
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
       BasicTextField(
           value = text,
           onValueChange = {
           text=it
           onSearch(it)
           },
           maxLines = 1,
           singleLine = true,
           textStyle = TextStyle().copy(Color.Black),
           modifier = Modifier.fillMaxWidth()
               .shadow(5.dp, CircleShape)
               .background(Color.White, CircleShape)
               .padding(horizontal = 20.dp, vertical = 12.dp)
               .onFocusChanged {
                   isHintDisplayed != it.isFocused
               }
       )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                    .align(Alignment.CenterStart)
                    
            )
        }
    }


}

@Composable
@Preview(showBackground = true)
fun PokemonListScreenPreview(){
    ComposePokedexTheme {
        PokemonListScreen()

    }
}
