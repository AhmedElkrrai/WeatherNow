package com.example.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherDto(
    val name: String,
    val weather: List<WeatherDto>,
)
