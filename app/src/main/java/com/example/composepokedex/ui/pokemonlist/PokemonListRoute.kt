package com.example.composepokedex.ui.pokemonlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable
import ph.theorangeco.data.models.states.isSuccess
import timber.log.Timber
@Serializable
object PokemonList
@Composable
fun PokemonListRoute(navBackStackEntry: NavBackStackEntry) {
    val viewModel = hiltViewModel<PokemonListViewmodel>()

    val pokemonList by remember { mutableStateOf(viewModel.pokemons) }
    val pokemonNameList by viewModel.pokemonNameList.collectAsState()
  //  SwipeableCardScreen()
    PokemonListScreen(
        pokemonList,
        onSearchBarTextChange = {
            viewModel.getPokemonNameSuggestion(it)
        }
        
    )
    LaunchedEffect(pokemonNameList) {
        if (pokemonNameList.isSuccess())
        Timber.tag("POKEMON_APP").d(pokemonNameList.toString())
    }

}