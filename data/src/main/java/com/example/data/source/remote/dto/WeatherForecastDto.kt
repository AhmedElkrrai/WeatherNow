package com.example.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastDto(
    @Json(name = "list") val list: List<ForecastItemDto>
)
