package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShade(
    @SerializedName("Maximum")
    val maximum: MaximumXX? = MaximumXX(),
    @SerializedName("Minimum")
    val minimum: MinimumXX? = MinimumXX()
)