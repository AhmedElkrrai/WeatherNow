package com.example.forecast.domain.entities

import java.time.LocalDate

data class OneCallWeatherForecast(
    val timezone: String,
    val latitude: Double,
    val longitude: Double,
    val daily: List<DailyForecast>
) {
    data class DailyForecast(
        val date: LocalDate,
        val temperature: Temperature,
        val weather: Weather,
        val humidity: Int,
        val wind: Wind
    )

    data class Temperature(
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double,
        val eve: Double,
        val morn: Double
    )

    data class Weather(
        val type: String,
        val description: String,
        val icon: String
    )

    data class Wind(
        val speed: Double,
        val direction: Int
    )
}
