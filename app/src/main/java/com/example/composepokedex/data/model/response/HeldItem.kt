package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class HeldItem(
    @SerializedName("item")
    val item: com.example.composepokedex.data.model.response.Item,
    @SerializedName("version_details")
    val versionDetails: List<com.example.composepokedex.data.model.response.VersionDetail>
)