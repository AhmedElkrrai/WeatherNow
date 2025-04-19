package com.example.data.mappers

import com.example.core.domain.ConditionType
import com.example.core.domain.Coordinates
import com.example.data.source.remote.dto.ForecastResponseDto
import com.example.forecast.domain.entities.WeatherForecast
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun ForecastResponseDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        city = WeatherForecast.City(
            name = city.name,
            country = city.country,
            coordinates = Coordinates(
                latitude = city.coordinates.latitude,
                longitude = city.coordinates.longitude
            )
        ),
        dailyForecasts = forecastItems
            .groupBy { it.dateTime.toLocalDate() }
            .map { (date, items) ->
                // Daily summary calculations
                val tempMax = items.maxOfOrNull { it.main.tempMax } ?: 0.0
                val tempMin = items.minOfOrNull { it.main.tempMin } ?: 0.0
                val tempAvg = items.map { it.main.temp }.average()

                // Time-specific temperatures
                val morningTemp = items.findClosestToHour(9)?.main?.temp ?: tempAvg
                val dayTemp = items.findClosestToHour(15)?.main?.temp ?: tempAvg
                val eveningTemp = items.findClosestToHour(18)?.main?.temp ?: tempAvg
                val nightTemp = items.findClosestToHour(3)?.main?.temp ?: tempAvg

                // Main item for day's primary condition (typically midday)
                val mainItem = items.findClosestToHour(15) ?: items.first()

                // Create hourly forecasts
                val hourlyForecasts = items.map { item ->
                    WeatherForecast.DailyForecast.HourlyForecast(
                        time = item.dateTime.toLocalTime(),
                        temperature = item.main.temp,
                        iconCode = item.weather.firstOrNull()?.icon.orEmpty()
                    )
                }.sortedBy { it.time }

                WeatherForecast.DailyForecast(
                    date = date,
                    temperature = WeatherForecast.DailyForecast.Temperature(
                        day = dayTemp,
                        min = tempMin,
                        max = tempMax,
                        night = nightTemp,
                        evening = eveningTemp,
                        morning = morningTemp
                    ),
                    condition = WeatherForecast.DailyForecast.WeatherCondition(
                        type = mainItem.weather.firstOrNull()?.toConditionType()
                            ?: ConditionType.UNKNOWN,
                        description = mainItem.weather.firstOrNull()?.description.orEmpty(),
                        iconCode = mainItem.weather.firstOrNull()?.icon.orEmpty()
                    ),
                    precipitation = WeatherForecast.DailyForecast.Precipitation(
                        probability = mainItem.precipitationProbability ?: 0.0,
                        rainVolume = mainItem.rain?.threeHourVolume,
                        snowVolume = mainItem.snow?.threeHourVolume
                    ),
                    wind = WeatherForecast.DailyForecast.Wind(
                        speed = mainItem.wind?.speed ?: 0.0,
                        direction = mainItem.wind?.degrees ?: 0,
                        gustSpeed = mainItem.wind?.gust
                    ),
                    humidity = mainItem.main.humidity,
                    pressure = mainItem.main.pressure,
                    hourlyForecasts = hourlyForecasts
                )
            }
            .sortedBy { it.date }
    )
}

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

private fun Long.toLocalDate(): LocalDate {
    return toLocalDateTime().toLocalDate()
}

private fun Long.toLocalTime(): LocalTime {
    return toLocalDateTime().toLocalTime()
}

private fun List<ForecastResponseDto.ForecastItem>.findClosestToHour(
    targetHour: Int
): ForecastResponseDto.ForecastItem? {
    return this.minByOrNull { item ->
        val hour = item.dateTime.toLocalDateTime().hour
        Math.abs(hour - targetHour)
    }
}
