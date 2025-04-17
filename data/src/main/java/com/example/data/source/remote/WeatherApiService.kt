package com.example.data.source.remote

import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.dto.ForecastResponseDto
import com.example.data.source.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "BuildConfig.WEATHER_API_KEY"
    ): Result<WeatherResponseDto, DataError>

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "BuildConfig.WEATHER_API_KEY"
    ): Result<ForecastResponseDto, DataError>
}
