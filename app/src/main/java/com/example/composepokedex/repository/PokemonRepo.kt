package com.example.composepokedex.repository;


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.composepokedex.data.db.PokemonDb
import com.example.composepokedex.data.model.response.Pokemon
import com.example.composepokedex.data.model.response.PokemonList
import com.example.composepokedex.data.model.response.PokemonName
import com.example.composepokedex.data.model.response.Result
import com.example.composepokedex.data.remote.PokeService
import com.example.composepokedex.util.Constants.PAGE_SIZE
import com.example.composepokedex.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


interface IPokemonRepo {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonList>

    suspend fun getPokemonListf(limit: Int, offset: Int): Flow<Response<PokemonList>>
    suspend fun getPokemonInfo(pokemonName: String): Response<Pokemon>
    suspend fun getPokemonInfof(pokemonName: String): Flow<Response<Pokemon>>
    fun getPokemonListViaPaging(): Flow<PagingData<Result>>
    suspend fun insertPokemonList(pokemons: List<PokemonName>)
    suspend fun getPokemonNameList(name: String): Flow<List<PokemonName>>
    suspend fun getAllPokemon():Flow<List<PokemonName>>
}



@ActivityScoped
class PokemonRepo @Inject constructor(
    private val PokeApi:PokeService,
    private val PokemonDb: PokemonDb
) :IPokemonRepo {

    override suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonList> {
        val response = try {
            PokeApi.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }
    override suspend fun insertPokemonList(pokemons: List<PokemonName>){
        PokemonDb.pokemonDao().insert(pokemons)
    }

    override suspend fun getPokemonNameList(name: String): Flow<List<PokemonName>> {
        return PokemonDb.pokemonDao().searchPokemon(name)
    }

    override suspend fun getAllPokemon():Flow<List<PokemonName>>  {
        return PokemonDb.pokemonDao().getAllPokemon()
    }

    override suspend fun getPokemonListf(limit: Int, offset: Int): Flow<Response<PokemonList>> =
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

    override suspend fun getPokemonInfo(pokemonName: String): Response<Pokemon> {
        val response = try {
            PokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }

    override suspend fun getPokemonInfof(pokemonName: String): Flow<Response<Pokemon>> = flow{
        try {
            val data = PokeApi.getPokemonInfo(pokemonName)
            emit(Response.Success(data))
        } catch (e: Exception){

            emit(Response.Error("${e.message}"))
        } catch (e: HttpException){
            emit(Response.Error("${e.message}"))
        }
    }

    override fun getPokemonListViaPaging(): Flow<PagingData<Result>> {
        return Pager(
            PagingConfig(pageSize = PAGE_SIZE)
        ){
            PokemonListPagingSource(pokeApi = PokeApi)
        }.flow

    }

}
