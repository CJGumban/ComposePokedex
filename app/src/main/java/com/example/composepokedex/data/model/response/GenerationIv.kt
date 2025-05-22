package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl")
    val diamondPearl: com.example.composepokedex.data.model.response.DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: com.example.composepokedex.data.model.response.HeartgoldSoulsilver,
    @SerializedName("platinum")
    val platinum: com.example.composepokedex.data.model.response.Platinum
)