package com.example.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherDto(
    @Json(name = "coord") val coordinates: Coordinates?,
    @Json(name = "weather") val weather: List<WeatherDto>,
    @Json(name = "base") val base: String?,
    @Json(name = "main") val main: Main,
    @Json(name = "visibility") val visibility: Int?,
    @Json(name = "wind") val wind: Wind?,
    @Json(name = "clouds") val clouds: Clouds?,
    @Json(name = "dt") val dateTime: Long,
    @Json(name = "sys") val sys: Sys?,
    @Json(name = "timezone") val timezone: Int?,
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val name: String,
    @Json(name = "cod") val cod: Int?
) {
    @JsonClass(generateAdapter = true)
    data class Coordinates(
        @Json(name = "lon") val longitude: Double,
        @Json(name = "lat") val latitude: Double
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @Json(name = "temp") val temp: Double,
        @Json(name = "feels_like") val feelsLike: Double,
        @Json(name = "temp_min") val tempMin: Double,
        @Json(name = "temp_max") val tempMax: Double,
        @Json(name = "pressure") val pressure: Int,
        @Json(name = "humidity") val humidity: Int,
        @Json(name = "sea_level") val seaLevel: Int?,
        @Json(name = "grnd_level") val groundLevel: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        @Json(name = "speed") val speed: Double,
        @Json(name = "deg") val degrees: Int,
        @Json(name = "gust") val gust: Double?
    )

    @JsonClass(generateAdapter = true)
    data class Clouds(
        @Json(name = "all") val all: Int
    )

    @JsonClass(generateAdapter = true)
    data class Sys(
        @Json(name = "type") val type: Int?,
        @Json(name = "id") val id: Int?,
        @Json(name = "country") val country: String?,
        @Json(name = "sunrise") val sunrise: Long?,
        @Json(name = "sunset") val sunset: Long?
    )
}
