package com.example.forecast.domain

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.forecast.domain.entities.OneCallWeatherForecast
import com.example.forecast.domain.entities.WeatherForecast

interface ForecastRepository {
    suspend fun getWeatherForecast(city: City): Result<WeatherForecast, DataError>
    suspend fun getDailyForecast(city: City): Result<OneCallWeatherForecast, DataError>
}
