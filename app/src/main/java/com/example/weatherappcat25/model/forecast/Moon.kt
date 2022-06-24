package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Moon(
    @SerializedName("Age")
    val age: Int? = null,
    @SerializedName("EpochRise")
    val epochRise: Int? = null,
    @SerializedName("EpochSet")
    val epochSet: Int? = null,
    @SerializedName("Phase")
    val phase: String? = null,
    @SerializedName("Rise")
    val rise: String? = null,
    @SerializedName("Set")
    val `set`: String? = null
)