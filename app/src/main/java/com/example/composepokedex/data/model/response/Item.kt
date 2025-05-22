package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)