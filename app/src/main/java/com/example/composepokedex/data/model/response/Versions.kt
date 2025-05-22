package com.example.composepokedex.data.model.response


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    val generationI: com.example.composepokedex.data.model.response.GenerationI,
    @SerializedName("generation-ii")
    val generationIi: com.example.composepokedex.data.model.response.GenerationIi,
    @SerializedName("generation-iii")
    val generationIii: com.example.composepokedex.data.model.response.GenerationIii,
    @SerializedName("generation-iv")
    val generationIv: com.example.composepokedex.data.model.response.GenerationIv,
    @SerializedName("generation-v")
    val generationV: com.example.composepokedex.data.model.response.GenerationV,
    @SerializedName("generation-vi")
    val generationVi: com.example.composepokedex.data.model.response.GenerationVi,
    @SerializedName("generation-vii")
    val generationVii: com.example.composepokedex.data.model.response.GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: com.example.composepokedex.data.model.response.GenerationViii
)