package com.example.currentweather.presentation

import com.example.core.domain.DataError

sealed class WeatherEvent {
    data class ShowError(val error: DataError) : WeatherEvent()
    data class Navigate(val route: String) : WeatherEvent()
}