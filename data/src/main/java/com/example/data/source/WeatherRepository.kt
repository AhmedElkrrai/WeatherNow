package com.example.data.source

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.dto.CurrentWeatherDto
import com.example.data.source.remote.dto.WeatherForecastDto

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Result<CurrentWeatherDto, DataError>
    suspend fun getWeatherForecast(city: String): Result<WeatherForecastDto, DataError>
}
