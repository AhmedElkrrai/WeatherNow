package com.example.data.source

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.WeatherApiService
import com.example.data.source.remote.dto.CurrentWeatherDto
import com.example.data.source.remote.dto.WeatherForecastDto
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String): Result<CurrentWeatherDto, DataError> {
        return apiService.getCurrentWeather(city)
    }

    override suspend fun getWeatherForecast(city: String): Result<WeatherForecastDto, DataError> {
        return apiService.getWeatherForecast(city)
    }
}
