package com.example.weatherappcat25.network

import com.example.weatherappcat25.domain.mapToDomainForecast
import com.example.weatherappcat25.domain.mapToDomainZipCode
import com.example.weatherappcat25.utils.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface WeatherRepository {
    fun getLocationKeyByZipCode(zipCode: String): Flow<ResponseState>
    fun getDailyForecast(locationKey: String): Flow<ResponseState>
}

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override fun getLocationKeyByZipCode(zipCode: String): Flow<ResponseState> =
        flow {
            emit(ResponseState.LOADING)
            //delay(2000)

            try {
                val response = weatherApi.getLocationByZipCode(zipCode)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseState.SUCCESS(it.mapToDomainZipCode()))
                    } ?: throw Exception(RESPONSE_NULL)
                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } catch (e: Exception) {
                emit(ResponseState.ERROR(e))
            }
        }

    override fun getDailyForecast(locationKey: String): Flow<ResponseState> =
        flow {
            emit(ResponseState.LOADING)
            delay(2000)

            try {
                val response = weatherApi.getOneDayForecast(locationKey)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseState.SUCCESS(it.mapToDomainForecast().first()))
                    } ?: throw Exception(RESPONSE_NULL)
                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } catch (e: Exception) {
                emit(ResponseState.ERROR(e))
            }
        }

    companion object {
        private const val RESPONSE_NULL = "RESPONSE IS NULL"
    }

}