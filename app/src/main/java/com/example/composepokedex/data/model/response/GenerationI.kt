package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: com.example.composepokedex.data.model.response.RedBlue,
    @SerializedName("yellow")
    val yellow: com.example.composepokedex.data.model.response.Yellow
)