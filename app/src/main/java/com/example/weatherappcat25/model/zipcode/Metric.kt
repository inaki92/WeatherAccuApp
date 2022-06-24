package com.example.weatherappcat25.model.zipcode


import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("Unit")
    val unit: String? = null,
    @SerializedName("UnitType")
    val unitType: Int? = null,
    @SerializedName("Value")
    val value: Double? = null
)