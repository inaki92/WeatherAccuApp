package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Night(
    @SerializedName("CloudCover")
    val cloudCover: Int? = 0,
    @SerializedName("Evapotranspiration")
    val evapotranspiration: EvapotranspirationX? = EvapotranspirationX(),
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
    val ice: IceX? = IceX(),
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
    val rain: RainX? = RainX(),
    @SerializedName("RainProbability")
    val rainProbability: Int? = 0,
    @SerializedName("ShortPhrase")
    val shortPhrase: String? = "",
    @SerializedName("Snow")
    val snow: SnowX? = SnowX(),
    @SerializedName("SnowProbability")
    val snowProbability: Int? = 0,
    @SerializedName("SolarIrradiance")
    val solarIrradiance: SolarIrradianceX? = SolarIrradianceX(),
    @SerializedName("ThunderstormProbability")
    val thunderstormProbability: Int? = 0,
    @SerializedName("TotalLiquid")
    val totalLiquid: TotalLiquidX? = TotalLiquidX(),
    @SerializedName("Wind")
    val wind: WindX? = WindX(),
    @SerializedName("WindGust")
    val windGust: WindGustX? = WindGustX()
)