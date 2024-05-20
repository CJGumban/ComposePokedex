package com.example.composepokedex.repository;


import com.example.composepokedex.data.remote.PokeService
import com.example.composepokedex.data.remote.response.Pokemon
import com.example.composepokedex.data.remote.response.PokemonList
import com.example.composepokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject



interface IPokemonRepo {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>
}



@ActivityScoped
public class PokemonRepo @Inject constructor(
        private val PokeApi:PokeService
) :IPokemonRepo {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            PokeApi.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            PokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }

}
