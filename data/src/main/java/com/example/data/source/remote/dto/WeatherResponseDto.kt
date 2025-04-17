package com.example.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseDto(
    val name: String,
    val weather: List<WeatherDto>,
)
