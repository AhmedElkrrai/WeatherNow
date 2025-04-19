package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.currentweather.domain.WeatherRepository
import com.example.currentweather.domain.entities.CurrentWeather
import com.example.data.mappers.toDomain
import com.example.data.source.remote.WeatherApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherApiService
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: City): Result<CurrentWeather, DataError> {
        return try {
            val current = remoteSource
                .getCurrentWeather(
                    longitude = city.longitude,
                    latitude = city.latitude
                )
                .toDomain()
            Result.Success(current)
        } catch (e: Exception) {
            Result.Error(DataError.FailedToRetrieveData)
        }
    }
}