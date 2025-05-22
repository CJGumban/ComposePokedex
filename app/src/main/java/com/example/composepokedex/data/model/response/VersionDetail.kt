package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int,
    @SerializedName("version")
    val version: com.example.composepokedex.data.model.response.Version
)