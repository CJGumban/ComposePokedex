package com.example.composepokedex.data.api.service

import com.example.composepokedex.data.model.evolutionChain.EvolutionChain
import com.example.composepokedex.data.model.pokemonInfo.PokemonSpecies
import com.example.composepokedex.data.model.response.Pokemon
import com.example.composepokedex.data.model.response.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") pokemon: String
    ): Response<Pokemon>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") pokemon: String
    ): PokemonSpecies

    @GET("evolution-chainLink{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: Int
    ):EvolutionChain
}