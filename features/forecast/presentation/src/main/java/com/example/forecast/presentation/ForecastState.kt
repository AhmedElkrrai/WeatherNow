package com.example.forecast.presentation

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.forecast.domain.entities.OneCallWeatherForecast

sealed class ForecastState {
    data object Loading : ForecastState()

    data class Success(
        val city: City,
        val forecast: OneCallWeatherForecast,
    ) : ForecastState()

    data class Error(
        val error: DataError,
        val city: City? = null
    ) : ForecastState()
}