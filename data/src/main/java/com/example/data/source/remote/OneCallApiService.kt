package com.example.data.source.remote

import com.example.data.source.remote.dto.OneCallForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallApiService {
    @GET("data/3.0/onecall")
    suspend fun getDailyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): OneCallForecastResponseDto
}
