package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class DegreeDaySummary(
    @SerializedName("Cooling")
    val cooling: Cooling? = null,
    @SerializedName("Heating")
    val heating: Heating? = null
)