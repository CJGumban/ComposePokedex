package com.example.composepokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerationViX(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphireX,
    @SerializedName("x-y")
    val xY: XYX
)