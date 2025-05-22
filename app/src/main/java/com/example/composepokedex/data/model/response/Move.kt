package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: com.example.composepokedex.data.model.response.MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<com.example.composepokedex.data.model.response.VersionGroupDetail>
)