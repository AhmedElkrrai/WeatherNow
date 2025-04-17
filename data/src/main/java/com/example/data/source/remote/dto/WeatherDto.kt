package com.example.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)