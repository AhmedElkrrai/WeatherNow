package com.example.currentweather.presentation

import com.example.core.domain.City
import com.example.currentweather.domain.entities.CurrentWeather

data class WeatherViewState(
    val currentWeather: CurrentWeather? = null,
    val city: City? = null,
    val isLoading: Boolean = false,
)