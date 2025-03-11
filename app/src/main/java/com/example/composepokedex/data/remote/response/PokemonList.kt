package com.example.composepokedex.data.remote.response


data class PokemonList(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    var results: List<Result>
)