package com.example.weatherappcat25.model.zipcode


import com.google.gson.annotations.SerializedName

data class Elevation(
    @SerializedName("Imperial")
    val imperial: Imperial? = null,
    @SerializedName("Metric")
    val metric: Metric? = null
)