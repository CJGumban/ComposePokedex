package com.example.composepokedex.data.model.evolutionChain

import com.example.composepokedex.data.model.pokemonInfo.PokemonSpecies

data class EvolvesTo(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: PokemonSpecies
)