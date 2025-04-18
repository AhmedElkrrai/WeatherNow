package com.example.data.source.remote

import com.example.data.source.remote.dto.CurrentWeatherDto
import com.example.data.source.remote.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String
    ): CurrentWeatherDto

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("cnt") days: Int = 7
    ): ForecastResponseDto
}
