package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class WindGustX(
    @SerializedName("Direction")
    val direction: Direction? = Direction(),
    @SerializedName("Speed")
    val speed: Speed? = Speed()
)