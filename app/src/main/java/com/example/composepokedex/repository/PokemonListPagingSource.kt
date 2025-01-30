package com.example.composepokedex.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composepokedex.data.remote.PokeService
import com.example.composepokedex.data.remote.response.Result
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val pokeApi: PokeService
) : PagingSource<Int, Result>(){
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {

            val nextPageKey = params.key?.inc()?:1
            val offset = params.key?.times(5)?:1
            val response = pokeApi.getPokemonList(offset = offset, limit = 5)

            return LoadResult.Page(
                data = response.results,
                nextKey = nextPageKey,
                prevKey = null
            )
        } catch (ex: IOException){
            Timber.tag("POKEMON_LIST_PAGING_SOURCE").e(ex)
            return LoadResult.Error(ex)
        } catch (ex: HttpException){
            Timber.tag("POKEMON_LIST_PAGING_SOURCE").e(ex)
            return LoadResult.Error(ex)
        }
    }

}