package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: com.example.composepokedex.data.model.response.BlackWhite
)