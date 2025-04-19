package com.example.currentweather.domain.entities

import com.example.core.domain.ConditionType
import com.example.core.domain.Coordinates
import java.time.LocalDateTime

data class CurrentWeather(
    val cityName: String = "",
    val coordinates: Coordinates = Coordinates(0.0, 0.0),
    val temperature: Temperature = Temperature(0.0, 0.0, 0.0, 0.0),
    val condition: WeatherCondition = WeatherCondition(ConditionType.UNKNOWN, "", ""),
    val wind: Wind = Wind(0.0, 0, null),
    val humidity: Int = 0,
    val pressure: Int = 0,
    val visibility: Int = 0,
    val sunrise: LocalDateTime? = null,
    val sunset: LocalDateTime? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
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
