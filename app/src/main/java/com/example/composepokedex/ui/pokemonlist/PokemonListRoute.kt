package com.example.composepokedex.ui.pokemonlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.example.composepokedex.data.handlers.Loghandler
import com.example.composepokedex.data.model.ParsedPokemon
import com.example.composepokedex.data.model.PokedexListEntry
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import ph.theorangeco.data.models.states.isSuccess
import timber.log.Timber

@Serializable
object PokemonList
@OptIn(ExperimentalSerializationApi::class)
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


