package com.example.weatherappcat25.network

import com.example.weatherappcat25.model.forecast.Forecast
import com.example.weatherappcat25.model.zipcode.ZipCodesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET(ZIP_CODE_SEARCH)
    suspend fun getLocationByZipCode(
        @Query("q") zipCode: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<List<ZipCodesItem>>

    @GET(FORECAST_SEARCH)
    suspend fun getOneDayForecast(
        @Path("locationKey") locationKey: String
    ): Response<Forecast>

    companion object {
        const val BASE_URL = "https://dataservice.accuweather.com/"

        private const val API_KEY = "JVdtKMS4Geip4T7bITJC6J2PzNJDY2mY"
        private const val ZIP_CODE_SEARCH = "locations/v1/postalcodes/search"
        private const val FORECAST_SEARCH = "forecasts/v1/daily/1day/{locationKey}"
    }

}