package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.mappers.toDomain
import com.example.data.source.remote.OneCallApiService
import com.example.data.source.remote.WeatherForecastApiService
import com.example.forecast.domain.ForecastRepository
import com.example.forecast.domain.entities.OneCallWeatherForecast
import com.example.forecast.domain.entities.WeatherForecast
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherForecastApiService,
    private val dailForecastService: OneCallApiService
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
            Result.Error(DataError.FailedToRetrieveData)
        }
    }

    override suspend fun getDailyForecast(city: City): Result<OneCallWeatherForecast, DataError> {
        return try {
            val forecast = dailForecastService
                .getDailyForecast(
                    longitude = city.longitude,
                    latitude = city.latitude
                )
                .toDomain()
            Result.Success(forecast)
        } catch (e: Exception) {
            Result.Error(DataError.FailedToRetrieveData)
        }
    }
}