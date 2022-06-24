package com.example.weatherappcat25.domain

import com.example.weatherappcat25.model.zipcode.TimeZone
import com.example.weatherappcat25.model.zipcode.ZipCodesItem

data class DomainZipCode(
    val locationKey: String,
    val cityName: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZone: TimeZone,
    val postalCode: String
)

fun List<ZipCodesItem>.mapToDomainZipCode(): List<DomainZipCode> {
    return this.map {
        DomainZipCode(
            locationKey = it.key ?: "Invalid key",
            cityName = it.englishName ?: it.localizedName ?: "Invalid city",
            country = it.country?.englishName ?: it.country?.localizedName ?: "Invalid country",
            latitude = it.geoPosition?.latitude ?: 0.0,
            longitude = it.geoPosition?.longitude ?: 0.0,
            timeZone = it.timeZone ?: TimeZone(),
            postalCode = it.primaryPostalCode ?: "Invalid zip code"
        )
    }
}
