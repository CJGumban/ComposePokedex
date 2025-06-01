package com.example.composepokedex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepokedex.ui.pokemonlist.PokemonList
import com.example.composepokedex.ui.pokemonlist.PokemonListRoute
import com.example.composepokedex.ui.pokemonstats.PokemonStats
import com.example.composepokedex.ui.pokemonstats.PokemonStatsRoute


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonList){


        composable<PokemonList> {
            PokemonListRoute(
                navBackStackEntry = it,
                navController= navController)

        }

        composable<PokemonStats>{
            PokemonStatsRoute(
                navBackStackEntry = it,
                navController = navController
            )
        }

    }
}