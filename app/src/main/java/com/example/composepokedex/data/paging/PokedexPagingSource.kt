package com.example.composepokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.composepokedex.data.db.PokemonDao
import com.example.composepokedex.data.model.PokedexListEntry
import okio.IOException
import javax.inject.Inject

class PokedexPagingSource @Inject constructor(
    private val pokemonDao: PokemonDao
) : PagingSource<Int, PokedexListEntry>(){
    override fun getRefreshKey(state: PagingState<Int, PokedexListEntry>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.inc()

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexListEntry> {
        return try {
            val pageNumber = params.key ?: 1
            val response = pokemonDao.getPagedList(
                limit = params.loadSize,
                offset = if(params.key!!.toInt()==1) 0 else (params.key!!.toInt()) * (params.loadSize)
            )

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = pageNumber.inc()
            )
        }
        catch(e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}