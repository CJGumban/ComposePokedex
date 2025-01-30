package com.example.composepokedex.data.remote.response


import com.google.gson.annotations.SerializedName

data class Cries(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String
)