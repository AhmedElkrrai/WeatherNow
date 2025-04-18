package com.example.forecast.domain

import com.example.cityinput.domain.entities.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.forecast.domain.entities.WeatherForecast

interface ForecastRepository {
    suspend fun getWeatherForecast(city: City): Result<WeatherForecast, DataError>
}
