package com.example.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastItemDto(
    val dt: Long,
    val weather: List<WeatherDto>
)
