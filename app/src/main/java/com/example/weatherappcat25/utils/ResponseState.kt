package com.example.weatherappcat25.utils

sealed class ResponseState {
    object LOADING : ResponseState()
    data class SUCCESS<T>(val response: T) : ResponseState()
    data class ERROR(val error: Exception) : ResponseState()
}
