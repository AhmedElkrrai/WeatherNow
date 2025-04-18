package com.example.forecast.domain.entities

import com.example.core.domain.ConditionType
import com.example.core.domain.Coordinates
import java.time.LocalDate

data class WeatherForecast(
    val city: City,
    val dailyForecasts: List<DailyForecast>
) {
    data class City(
        val name: String,
        val country: String,
        val coordinates: Coordinates
    )

    data class DailyForecast(
        val date: LocalDate,
        val temperature: Temperature,
        val condition: WeatherCondition,
        val precipitation: Precipitation,
        val wind: Wind,
        val humidity: Int,
        val pressure: Int
    ) {
        data class Temperature(
            val day: Double,
            val min: Double,
            val max: Double,
            val night: Double,
            val evening: Double,
            val morning: Double
        )

        data class WeatherCondition(
            val type: ConditionType,
            val description: String,
            val iconCode: String
        )

        data class Precipitation(
            val probability: Double,
            val rainVolume: Double?,
            val snowVolume: Double?
        )

        data class Wind(
            val speed: Double,
            val direction: Int,
            val gustSpeed: Double?
        )
    }
}
