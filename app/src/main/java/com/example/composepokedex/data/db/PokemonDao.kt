package com.example.composepokedex.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composepokedex.data.model.response.PokemonName

interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonName: List<PokemonName>)

    @Query("SELECT * FROM pokemonName")
}