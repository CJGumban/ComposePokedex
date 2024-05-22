package com.example.composepokedex.data.remote.response

import com.google.gson.annotations.SerializedName


data class GenerationIX(
    @SerializedName("red-blue")
    val redBlue: RedBlueX,
    val yellow: YellowX
)