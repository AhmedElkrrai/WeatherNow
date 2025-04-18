package com.example.data.source

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.entities.CurrentWeather
import com.example.data.source.remote.entities.WeatherForecast

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Result<CurrentWeather, DataError>
    suspend fun getWeatherForecast(city: String): Result<WeatherForecast, DataError>
}
