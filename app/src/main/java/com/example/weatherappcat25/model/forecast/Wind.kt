package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("Direction")
    val direction: Direction? = null,
    @SerializedName("Speed")
    val speed: Speed? = null
)