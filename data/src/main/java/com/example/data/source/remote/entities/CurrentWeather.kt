package com.example.data.source.remote.entities

import java.time.LocalDateTime

data class CurrentWeather(
    val cityName: String,
    val coordinates: Coordinates,
    val temperature: Temperature,
    val condition: WeatherCondition,
    val wind: Wind,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int,
    val sunrise: LocalDateTime?,
    val sunset: LocalDateTime?,
    val timestamp: LocalDateTime
) {
    data class Temperature(
        val current: Double,
        val feelsLike: Double,
        val min: Double,
        val max: Double
    )

    data class WeatherCondition(
        val type: ConditionType,
        val description: String,
        val iconCode: String
    )

    data class Wind(
        val speed: Double,
        val direction: Int, // degrees
        val gustSpeed: Double?
    )
}
