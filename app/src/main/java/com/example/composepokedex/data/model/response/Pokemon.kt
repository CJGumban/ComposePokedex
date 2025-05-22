package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class Pokemon(
    val abilities: List<com.example.composepokedex.data.model.response.Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val forms: List<com.example.composepokedex.data.model.response.Form>,
    @SerializedName("game_indices")
    val gameIndices: List<com.example.composepokedex.data.model.response.GameIndice>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<com.example.composepokedex.data.model.response.Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: com.example.composepokedex.data.model.response.Species,
    val sprites: com.example.composepokedex.data.model.response.Sprites,
    val stats: List<com.example.composepokedex.data.model.response.Stat>,
    val types: List<com.example.composepokedex.data.model.response.Type>,
    val weight: Int
)