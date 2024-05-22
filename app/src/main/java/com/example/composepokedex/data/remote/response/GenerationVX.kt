package com.example.composepokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerationVX(
    @SerializedName("black-white")
    val blackWhite: BlackWhiteX
)