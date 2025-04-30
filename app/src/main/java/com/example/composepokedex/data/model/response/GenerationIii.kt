package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @SerializedName("emerald")
    val emerald: com.example.composepokedex.data.model.response.Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: com.example.composepokedex.data.model.response.FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: com.example.composepokedex.data.model.response.RubySapphire
)