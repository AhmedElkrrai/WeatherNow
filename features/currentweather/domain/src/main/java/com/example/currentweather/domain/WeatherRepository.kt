package com.example.currentweather.domain

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.currentweather.domain.entities.CurrentWeather
import com.example.currentweather.domain.entities.WeatherForecast

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Result<CurrentWeather, DataError>
    suspend fun getWeatherForecast(city: String): Result<WeatherForecast, DataError>
}
