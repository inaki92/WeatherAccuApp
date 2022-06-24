package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("CloudCover")
    val cloudCover: Int? = 0,
    @SerializedName("Evapotranspiration")
    val evapotranspiration: Evapotranspiration? = Evapotranspiration(),
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean? = false,
    @SerializedName("HoursOfIce")
    val hoursOfIce: Double? = 0.0,
    @SerializedName("HoursOfPrecipitation")
    val hoursOfPrecipitation: Double? = 0.0,
    @SerializedName("HoursOfRain")
    val hoursOfRain: Double? = 0.0,
    @SerializedName("HoursOfSnow")
    val hoursOfSnow: Double? = 0.0,
    @SerializedName("Ice")
    val ice: Ice? = Ice(),
    @SerializedName("IceProbability")
    val iceProbability: Int? = 0,
    @SerializedName("Icon")
    val icon: Int? = 0,
    @SerializedName("IconPhrase")
    val iconPhrase: String? = "",
    @SerializedName("LongPhrase")
    val longPhrase: String? = "",
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int? = 0,
    @SerializedName("Rain")
    val rain: Rain? = Rain(),
    @SerializedName("RainProbability")
    val rainProbability: Int? = 0,
    @SerializedName("ShortPhrase")
    val shortPhrase: String? = "",
    @SerializedName("Snow")
    val snow: Snow? = Snow(),
    @SerializedName("SnowProbability")
    val snowProbability: Int? = 0,
    @SerializedName("SolarIrradiance")
    val solarIrradiance: SolarIrradiance? = SolarIrradiance(),
    @SerializedName("ThunderstormProbability")
    val thunderstormProbability: Int? = 0,
    @SerializedName("TotalLiquid")
    val totalLiquid: TotalLiquid? = TotalLiquid(),
    @SerializedName("Wind")
    val wind: Wind? = Wind(),
    @SerializedName("WindGust")
    val windGust: WindGust? = WindGust()
)