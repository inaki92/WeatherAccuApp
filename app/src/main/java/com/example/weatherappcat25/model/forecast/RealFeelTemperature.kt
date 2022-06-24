package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class RealFeelTemperature(
    @SerializedName("Maximum")
    val maximum: Maximum? = null,
    @SerializedName("Minimum")
    val minimum: Minimum? = null
)