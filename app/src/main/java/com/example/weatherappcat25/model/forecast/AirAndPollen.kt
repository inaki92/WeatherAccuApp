package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class AirAndPollen(
    @SerializedName("Category")
    val category: String? = null,
    @SerializedName("CategoryValue")
    val categoryValue: Int? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("Type")
    val type: String? = null,
    @SerializedName("Value")
    val value: Int? = null
)