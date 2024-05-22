package com.example.composepokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class VersionsX(
    @SerializedName("generation-i")
    val generationI: GenerationIX,
    @SerializedName("generation-ii")
    val generationIi: GenerationIiX,
    @SerializedName("generation-iii")
    val generationIii: GenerationIiiX,
    @SerializedName("generation-iv")
    val generationIv: GenerationIvX,
    @SerializedName("generation-v")
    val generationV: GenerationVX,
    @SerializedName("generation-vi")
    val generationVi: GenerationViX,
    @SerializedName("generation-vii")
    val generationVii: GenerationViiX,
    @SerializedName("generation-viii")
    val generationViii: GenerationViiiX
)