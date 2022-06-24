package com.example.weatherappcat25.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcat25.domain.DomainZipCode
import com.example.weatherappcat25.network.WeatherRepository
import com.example.weatherappcat25.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _forecast: MutableLiveData<ResponseState> = MutableLiveData(ResponseState.LOADING)
    val forecast: LiveData<ResponseState> get() = _forecast

    private var zipCode: String = "30067"

    fun getDailyForecast() {
        viewModelScope.launch {
            // here you are in main thread
            weatherRepository.getLocationKeyByZipCode(zipCode)
                .flowOn(ioDispatcher)
                .collect { codeState ->
                    when (codeState) {
                        is ResponseState.LOADING -> {
                            _forecast.postValue(codeState)
                        }
                        is ResponseState.SUCCESS<*> -> {
                            val zipCode = (codeState as ResponseState.SUCCESS<List<DomainZipCode>>).response
                           startCollectingForecast(zipCode.firstOrNull())
                        }
                        is ResponseState.ERROR -> {
                            _forecast.postValue(codeState)
                        }
                    }

                }
        }
    }

    private suspend fun startCollectingForecast(domainZipCode: DomainZipCode?) {
        domainZipCode?.let {
            weatherRepository.getDailyForecast(it.locationKey)
                .flowOn(ioDispatcher)
                .collect { forecastStatus ->
                    _forecast.postValue(forecastStatus)
                }
        } ?: _forecast.postValue(ResponseState.ERROR(Exception("NO LOCATION KEY FOUND")))
    }
}