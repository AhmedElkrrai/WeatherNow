package com.example.forecast.presentation

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.forecast.domain.entities.WeatherForecast

sealed class ForecastState {
  data  object Loading : ForecastState()

    data class Success(
        val city: City,
        val forecast: WeatherForecast,
    ) : ForecastState()

    data class Error(
        val error: DataError,
        val city: City? = null
    ) : ForecastState()
}