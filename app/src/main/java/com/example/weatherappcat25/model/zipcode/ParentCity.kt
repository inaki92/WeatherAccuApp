package com.example.weatherappcat25.model.zipcode


import com.google.gson.annotations.SerializedName

data class ParentCity(
    @SerializedName("EnglishName")
    val englishName: String? = null,
    @SerializedName("Key")
    val key: String? = null,
    @SerializedName("LocalizedName")
    val localizedName: String? = null
)