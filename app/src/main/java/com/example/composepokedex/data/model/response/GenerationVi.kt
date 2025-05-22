package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: com.example.composepokedex.data.model.response.OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: com.example.composepokedex.data.model.response.XY
)