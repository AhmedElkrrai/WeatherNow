package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.mappers.toDomain
import com.example.data.source.remote.ForecastApiService
import com.example.forecast.domain.ForecastRepository
import com.example.forecast.domain.entities.WeatherForecast
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteSource: ForecastApiService
) : ForecastRepository {
    override suspend fun getWeatherForecast(city: City): Result<WeatherForecast, DataError> {
        return try {
            val forecast = remoteSource
                .getWeatherForecast(
                    longitude = city.longitude,
                    latitude = city.latitude
                )
                .toDomain()
            Result.Success(forecast)
        } catch (e: Exception) {
            Result.Error(DataError.ServerError)
        }
    }
}