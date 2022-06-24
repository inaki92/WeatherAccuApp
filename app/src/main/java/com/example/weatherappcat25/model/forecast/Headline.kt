package com.example.weatherappcat25.model.forecast


import com.google.gson.annotations.SerializedName

data class Headline(
    @SerializedName("Category")
    val category: String? = null,
    @SerializedName("EffectiveDate")
    val effectiveDate: String? = null,
    @SerializedName("EffectiveEpochDate")
    val effectiveEpochDate: Int? = null,
    @SerializedName("EndDate")
    val endDate: String? = null,
    @SerializedName("EndEpochDate")
    val endEpochDate: Int? = null,
    @SerializedName("Link")
    val link: String? = null,
    @SerializedName("MobileLink")
    val mobileLink: String? = null,
    @SerializedName("Severity")
    val severity: Int? = null,
    @SerializedName("Text")
    val text: String? = null
)