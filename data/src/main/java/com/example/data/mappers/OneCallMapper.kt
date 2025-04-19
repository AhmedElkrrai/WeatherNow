package com.example.data.mappers

import com.example.data.source.remote.dto.DailyDto
import com.example.data.source.remote.dto.OneCallForecastResponseDto
import com.example.forecast.domain.entities.OneCallWeatherForecast
import java.time.Instant
import java.time.ZoneOffset

fun OneCallForecastResponseDto.toDomain(): OneCallWeatherForecast {
    return OneCallWeatherForecast(
        timezone = timeZone,
        latitude = lat,
        longitude = lon,
        daily = daily.map { it.toDomain() }
    )
}

fun DailyDto.toDomain(): OneCallWeatherForecast.DailyForecast {
    return OneCallWeatherForecast.DailyForecast(
        date = Instant.ofEpochSecond(dateTime).atZone(ZoneOffset.UTC).toLocalDate(),
        temperature = OneCallWeatherForecast.Temperature(
            day = temp.day,
            min = temp.min,
            max = temp.max,
            night = temp.night,
            eve = temp.eve,
            morn = temp.morn
        ),
        weather = weather.firstOrNull()?.let {
            OneCallWeatherForecast.Weather(
                type = it.main,
                description = it.description,
                icon = it.icon
            )
        } ?: OneCallWeatherForecast.Weather("Unknown", "No data", ""),
        humidity = humidity,
        wind = OneCallWeatherForecast.Wind(
            speed = windSpeed,
            direction = windDeg
        )
    )
}
