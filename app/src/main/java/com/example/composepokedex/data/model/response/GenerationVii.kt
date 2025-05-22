package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons")
    val icons: com.example.composepokedex.data.model.response.Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: com.example.composepokedex.data.model.response.UltraSunUltraMoon
)