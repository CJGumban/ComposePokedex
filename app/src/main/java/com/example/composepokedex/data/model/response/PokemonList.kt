package com.example.composepokedex.data.model.response


data class PokemonList(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    var results: List<Result>
)