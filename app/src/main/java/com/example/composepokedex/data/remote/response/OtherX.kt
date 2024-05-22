package com.example.composepokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class OtherX(
    val dream_world: DreamWorldX,
    val home: HomeX,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkX,
    val showdown: ShowdownX
)