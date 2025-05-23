package com.example.data.source.remote

import com.example.data.source.remote.dto.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): CurrentWeatherDto
}
