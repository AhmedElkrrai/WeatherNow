package com.example.forecast.presentation

import com.example.core.domain.City

sealed interface ForecastAction {
    data class LoadForecast(val city: City) : ForecastAction
    data object Retry : ForecastAction
}