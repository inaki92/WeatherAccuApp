package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShade(
    @SerializedName("Maximum")
    val maximum: MaximumX? = MaximumX(),
    @SerializedName("Minimum")
    val minimum: MinimumX? = MinimumX()
)