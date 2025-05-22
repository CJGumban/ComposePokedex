package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: com.example.composepokedex.data.model.response.Crystal,
    @SerializedName("gold")
    val gold: com.example.composepokedex.data.model.response.Gold,
    @SerializedName("silver")
    val silver: com.example.composepokedex.data.model.response.Silver
)