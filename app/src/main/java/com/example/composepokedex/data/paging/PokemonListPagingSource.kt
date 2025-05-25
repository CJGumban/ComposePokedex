package com.example.composepokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composepokedex.data.model.response.Result
import com.example.composepokedex.data.api.service.PokeService
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
            val offset = params.key?.times(5)?:0
            var response = pokeApi.getPokemonList(offset = offset, limit = 5)
            var results = mutableListOf<Result>()
            response.results.forEachIndexed { index, result ->

                val number = if(result.url.endsWith("/")){
                    result.url.dropLast(1).takeLastWhile { it.isDigit() }
                }else{
                    result.url.takeLastWhile { it.isDigit() }
                }

           //     Timber.d("imgUrl: https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png")
                results.add(result.copy(
                    number = number.toInt(),
                    imgUrl =   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                ))
                Timber.d("asdf res: ${results}")

            }

            return LoadResult.Page(
                data = results,
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