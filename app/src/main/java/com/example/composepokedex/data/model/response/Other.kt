package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: com.example.composepokedex.data.model.response.DreamWorld,
    @SerializedName("home")
    val home: com.example.composepokedex.data.model.response.Home,
    @SerializedName("official-artwork")
    val officialArtwork: com.example.composepokedex.data.model.response.OfficialArtwork,
    @SerializedName("showdown")
    val showdown: com.example.composepokedex.data.model.response.Showdown
)