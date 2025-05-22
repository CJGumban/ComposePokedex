package com.example.composepokedex.data.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonName")
data class PokemonName(
    @PrimaryKey val name: String
)
