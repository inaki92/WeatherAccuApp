package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Minimum(
    @SerializedName("Phrase")
    val phrase: String? = null,
    @SerializedName("Unit")
    val unit: String? = null,
    @SerializedName("UnitType")
    val unitType: Int? = null,
    @SerializedName("Value")
    val value: Double? = null
)