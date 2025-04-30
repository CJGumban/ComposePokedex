package com.example.composepokedex.data.remote

import com.example.composepokedex.data.model.response.Pokemon
import com.example.composepokedex.data.model.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): com.example.composepokedex.data.model.response.PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") pokemon: String
    ): com.example.composepokedex.data.model.response.Pokemon
}