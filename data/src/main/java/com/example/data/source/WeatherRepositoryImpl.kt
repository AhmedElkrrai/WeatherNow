package com.example.data.source

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.mappers.toDomain
import com.example.data.source.remote.WeatherApiService
import com.example.data.source.remote.entities.CurrentWeather
import com.example.data.source.remote.entities.WeatherForecast
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherApiService
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<CurrentWeather, DataError> {
        return try {
            val current = remoteSource
                .getCurrentWeather(city)
                .toDomain()
            Result.Success(current)
        } catch (e: Exception) {
            Result.Error(DataError.ServerError)
        }
    }

    override suspend fun getWeatherForecast(city: String): Result<WeatherForecast, DataError> {
        return try {
            val forecast = remoteSource
                .getWeatherForecast(city)
                .toDomain()
            Result.Success(forecast)
        } catch (e: Exception) {
            Result.Error(DataError.ServerError)
        }
    }
}
