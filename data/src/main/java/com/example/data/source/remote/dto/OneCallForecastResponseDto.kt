package com.example.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OneCallForecastResponseDto(
    val lat: Double,
    val lon: Double,
    @Json(name = "timezone") val timeZone: String,
    @Json(name = "timezone_offset") val timezoneOffset: Int,
    val daily: List<DailyDto>
)

@JsonClass(generateAdapter = true)
data class DailyDto(
    @Json(name = "dt") val dateTime: Long,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: TempDto,
    val weather: List<WeatherDto>,
    val humidity: Int,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "wind_deg") val windDeg: Int
)

@JsonClass(generateAdapter = true)
data class TempDto(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
