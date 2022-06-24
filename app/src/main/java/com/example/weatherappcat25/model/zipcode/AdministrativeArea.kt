package com.example.weatherappcat25.model.zipcode


import com.google.gson.annotations.SerializedName

data class AdministrativeArea(
    @SerializedName("CountryID")
    val countryID: String? = null,
    @SerializedName("EnglishName")
    val englishName: String? = null,
    @SerializedName("EnglishType")
    val englishType: String? = null,
    @SerializedName("ID")
    val iD: String? = null,
    @SerializedName("Level")
    val level: Int? = null,
    @SerializedName("LocalizedName")
    val localizedName: String? = null,
    @SerializedName("LocalizedType")
    val localizedType: String? = null
)