package com.example.composepokedex.repository;


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.composepokedex.data.remote.PokeService
import com.example.composepokedex.data.model.response.Pokemon
import com.example.composepokedex.data.model.response.PokemonList
import com.example.composepokedex.data.model.response.Result
import com.example.composepokedex.util.Constants.PAGE_SIZE
import com.example.composepokedex.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


interface IPokemonRepo {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<com.example.composepokedex.data.model.response.PokemonList>

    suspend fun getPokemonListf(limit: Int, offset: Int): Flow<Response<com.example.composepokedex.data.model.response.PokemonList>>
    suspend fun getPokemonInfo(pokemonName: String): Response<com.example.composepokedex.data.model.response.Pokemon>
    suspend fun getPokemonInfof(pokemonName: String): Flow<Response<com.example.composepokedex.data.model.response.Pokemon>>

    fun getPokemonListViaPaging(): Flow<PagingData<com.example.composepokedex.data.model.response.Result>>
}



@ActivityScoped
class PokemonRepo @Inject constructor(
        private val PokeApi:PokeService
) :IPokemonRepo {
    override suspend fun getPokemonList(limit: Int, offset: Int): Response<com.example.composepokedex.data.model.response.PokemonList> {
        val response = try {
            PokeApi.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }

    override suspend fun getPokemonListf(limit: Int, offset: Int): Flow<Response<com.example.composepokedex.data.model.response.PokemonList>> =
        flow {
            try {
                val data = PokeApi.getPokemonList(limit, offset)
                emit(Response.Success(data))

            } catch (e: Exception){
                emit(Response.Error("${e.message}"))
            } catch (e: HttpException){
                emit(Response.Error("${e.message}"))
            }
        }

    override suspend fun getPokemonInfo(pokemonName: String): Response<com.example.composepokedex.data.model.response.Pokemon> {
        val response = try {
            PokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }

    override suspend fun getPokemonInfof(pokemonName: String): Flow<Response<com.example.composepokedex.data.model.response.Pokemon>> = flow{
        try {
            val data = PokeApi.getPokemonInfo(pokemonName)
            emit(Response.Success(data))
        } catch (e: Exception){

            emit(Response.Error("${e.message}"))
        } catch (e: HttpException){
            emit(Response.Error("${e.message}"))
        }
    }

    override fun getPokemonListViaPaging(): Flow<PagingData<com.example.composepokedex.data.model.response.Result>> {
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE)
        ){
            PokemonListPagingSource(pokeApi = PokeApi)
        }.flow

    }

}
