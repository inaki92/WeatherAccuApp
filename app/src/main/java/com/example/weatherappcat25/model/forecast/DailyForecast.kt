package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("AirAndPollen")
    val airAndPollen: List<AirAndPollen>? = listOf(),
    @SerializedName("Date")
    val date: String? = "",
    @SerializedName("Day")
    val day: Day? = Day(),
    @SerializedName("DegreeDaySummary")
    val degreeDaySummary: DegreeDaySummary? = DegreeDaySummary(),
    @SerializedName("EpochDate")
    val epochDate: Int? = 0,
    @SerializedName("HoursOfSun")
    val hoursOfSun: Double? = 0.0,
    @SerializedName("Link")
    val link: String? = "",
    @SerializedName("MobileLink")
    val mobileLink: String? = "",
    @SerializedName("Moon")
    val moon: Moon? = Moon(),
    @SerializedName("Night")
    val night: Night? = Night(),
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature? = RealFeelTemperature(),
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: RealFeelTemperatureShade? = RealFeelTemperatureShade(),
    @SerializedName("Sources")
    val sources: List<String>? = listOf(),
    @SerializedName("Sun")
    val sun: Sun? = Sun(),
    @SerializedName("Temperature")
    val temperature: Temperature? = Temperature()
)