package com.example.weatherappcat25.domain

import com.example.weatherappcat25.model.forecast.Forecast

data class DomainForecast(
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val maxRealTempFeel: String,
    val minRealTempFeel: String,
    val rainProbability: Int,
    val precipitationProbability: Int,
    val iceProbability: Int,
    val snowProbability: Int,
    val windSpeed: String
)

fun Forecast.mapToDomainForecast(): List<DomainForecast> {
    return this.dailyForecasts?.let {
        it.map { remote ->
            DomainForecast(
                date = remote.date ?: "invalid date",
                maxTemp = remote.temperature?.maximum?.value.toString() + " " + remote.temperature?.maximum?.unit,
                minTemp = remote.temperature?.minimum?.value.toString() + " " + remote.temperature?.minimum?.unit,
                maxRealTempFeel = remote.realFeelTemperature?.maximum?.value.toString() + " " + remote.realFeelTemperature?.maximum?.unit,
                minRealTempFeel = remote.realFeelTemperature?.minimum?.value.toString() + " " + remote.realFeelTemperature?.minimum?.unit,
                rainProbability = remote.day?.rainProbability ?: 0,
                precipitationProbability = remote.day?.precipitationProbability ?: 0,
                iceProbability = remote.day?.iceProbability ?: 0,
                snowProbability = remote.day?.snowProbability ?: 0,
                windSpeed = remote.day?.wind?.speed?.value.toString() + " " + remote.day?.wind?.speed?.unit
            )
        }
    } ?: emptyList()
}
