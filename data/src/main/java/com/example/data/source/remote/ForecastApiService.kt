package com.example.data.source.remote

import com.example.data.source.remote.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") days: Int = 7
    ): ForecastResponseDto
}
