package com.example.composepokedex.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composepokedex.data.model.PokedexListEntry
import com.example.composepokedex.data.model.response.PokemonName
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonName: List<PokemonName>)

    @Query("SELECT * FROM pokemonName WHERE name LIKE '%' || :search_query || '%' LIMIT 5")
    fun searchPokemon(search_query: String?): Flow<List<PokemonName>>


    @Query("SELECT * FROM pokemonName")
    fun getAllPokemon(): Flow<List<PokemonName>>

    @Query("SELECT * FROM pokedexListEntry LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<PokedexListEntry>


    @Query("SELECT * FROM pokedexListEntry")
    fun getPagedList2(): PagingSource<Int, PokedexListEntry>

    @Query("SELECT * FROM pokedexListEntry WHERE pokemonName LIKE '%' || :search_query || '%'")
    fun searchPokemon2(search_query: String?): PagingSource<Int, PokedexListEntry>

    @Query("SELECT * FROM pokemonName WHERE name LIKE '%' || :search_query || '%' LIMIT 5")
    fun searchFromPokedex(search_query: String?): Flow<List<PokemonName>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokedexListEntry(pokemonName: List<PokedexListEntry>)
}

