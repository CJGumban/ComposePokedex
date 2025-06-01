package com.example.composepokedex.ui.pokemonstats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.example.composepokedex.data.handlers.LogHandler
import kotlinx.serialization.Serializable
import ph.theorangeco.data.models.states.getData
import ph.theorangeco.data.models.states.isSuccess

@Serializable
data class PokemonStats(
    val id: String)

@Composable
fun PokemonStatsRoute(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController
){
    val viewModel: PokemonStatsViewmodel = hiltViewModel()
    val pokemonStats by viewModel.pokemonStats.collectAsState()
    val args =  navBackStackEntry.toRoute<PokemonStats>()
    PokemonStatsScreen(
        pokemon = pokemonStats
    )
    LaunchedEffect(Unit) {
        viewModel.getPokemonStats(args.id)
    }
    if(pokemonStats.isSuccess()){
      LogHandler.d("pokemonStats: ${pokemonStats.getData<Any>()}")
    }
}