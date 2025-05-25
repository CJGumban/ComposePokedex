package com.example.composepokedex.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pokedexListEntry")
data class PokedexListEntry(

    @PrimaryKey
    @SerializedName("name")
    val pokemonName: String,
    @SerializedName("url")
    val imageUrl: String,
    val number: Int = 0,

)
@Serializable
data class ParsedPokemon(
    val name: String,
    val url: String
)