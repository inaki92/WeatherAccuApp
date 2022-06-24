package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>? = listOf(),
    @SerializedName("Headline")
    val headline: Headline? = Headline()
)