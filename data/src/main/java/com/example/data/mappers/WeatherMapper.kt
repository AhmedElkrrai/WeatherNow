package com.example.data.mappers

import com.example.core.domain.ConditionType
import com.example.core.domain.Coordinates
import com.example.currentweather.domain.entities.CurrentWeather
import com.example.data.source.remote.dto.CurrentWeatherDto
import com.example.data.source.remote.dto.ForecastResponseDto
import com.example.data.source.remote.dto.WeatherDto
import com.example.forecast.domain.entities.WeatherForecast
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    return CurrentWeather(
        cityName = name,
        coordinates = Coordinates(
            latitude = coordinates?.latitude ?: 0.0,
            longitude = coordinates?.longitude ?: 0.0
        ),
        temperature = CurrentWeather.Temperature(
            current = main.temp,
            feelsLike = main.feelsLike,
            min = main.tempMin,
            max = main.tempMax
        ),
        condition = CurrentWeather.WeatherCondition(
            type = weather.firstOrNull()?.toConditionType() ?: ConditionType.UNKNOWN,
            description = weather.firstOrNull()?.description ?: "",
            iconCode = weather.firstOrNull()?.icon ?: ""
        ),
        wind = CurrentWeather.Wind(
            speed = wind?.speed ?: 0.0,
            direction = wind?.degrees ?: 0,
            gustSpeed = wind?.gust
        ),
        humidity = main.humidity,
        pressure = main.pressure,
        visibility = visibility ?: 0,
        sunrise = sys?.sunrise?.toLocalDateTime(),
        sunset = sys?.sunset?.toLocalDateTime(),
        timestamp = dateTime.toLocalDateTime()
    )
}

fun WeatherDto.toConditionType(): ConditionType {
    return when (id) {
        in 200..232 -> ConditionType.THUNDERSTORM
        in 300..321 -> ConditionType.DRIZZLE
        in 500..531 -> ConditionType.RAIN
        in 600..622 -> ConditionType.SNOW
        701, 711, 721, 731, 741, 751, 761, 762, 771, 781 -> ConditionType.ATMOSPHERE
        800 -> ConditionType.CLEAR
        in 801..804 -> ConditionType.CLOUDS
        else -> ConditionType.UNKNOWN
    }
}
