package com.example.composepokedex.ui.pokemonlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.composepokedex.ui.pokemonstats.PokemonStats
import com.example.composepokedex.ui.pokemonstats.PokemonStatsViewmodel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
object PokemonList
@OptIn(ExperimentalSerializationApi::class)
@Composable
fun PokemonListRoute(navBackStackEntry: NavBackStackEntry, navController: NavHostController) {

    val viewModel = hiltViewModel<PokemonListViewmodel>()
    val viewModel2 = hiltViewModel<PokemonStatsViewmodel>()

    val pokemonList by remember { mutableStateOf(viewModel.pokemons) }
    val pokemonNameList by viewModel.pokemonNameList.collectAsState()
  //  SwipeableCardScreen()
    PokemonListScreen(
        pokemonList,
        onSearchBarTextChange = {
            viewModel.getPokemonNameSuggestion(it)
        },
        onItemClick = {navController.navigate(PokemonStats(it.pokemonName))}
        
    )



}


