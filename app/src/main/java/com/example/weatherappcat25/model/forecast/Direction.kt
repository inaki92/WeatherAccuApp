package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Direction(
    @SerializedName("Degrees")
    val degrees: Int? = null,
    @SerializedName("English")
    val english: String? = null,
    @SerializedName("Localized")
    val localized: String? = null
)