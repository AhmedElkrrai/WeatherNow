package com.example.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponseDto(
    @Json(name = "cod") val code: String,
    @Json(name = "message") val message: Int,
    @Json(name = "cnt") val count: Int,
    @Json(name = "list") val forecastItems: List<ForecastItem>,
    @Json(name = "city") val city: City
) {
    @JsonClass(generateAdapter = true)
    data class ForecastItem(
        @Json(name = "dt") val dateTime: Long,
        @Json(name = "main") val main: Main,
        @Json(name = "weather") val weather: List<WeatherDto>,
        @Json(name = "clouds") val clouds: Clouds?,
        @Json(name = "wind") val wind: Wind?,
        @Json(name = "visibility") val visibility: Int?,
        @Json(name = "pop") val precipitationProbability: Double?,
        @Json(name = "rain") val rain: Precipitation?,
        @Json(name = "snow") val snow: Precipitation?,
        @Json(name = "sys") val sys: Sys?,
        @Json(name = "dt_txt") val dateTimeText: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Main(
            @Json(name = "temp") val temp: Double,
            @Json(name = "feels_like") val feelsLike: Double,
            @Json(name = "temp_min") val tempMin: Double,
            @Json(name = "temp_max") val tempMax: Double,
            @Json(name = "pressure") val pressure: Int,
            @Json(name = "sea_level") val seaLevel: Int?,
            @Json(name = "grnd_level") val groundLevel: Int?,
            @Json(name = "humidity") val humidity: Int,
            @Json(name = "temp_kf") val tempKf: Double?
        )

        @JsonClass(generateAdapter = true)
        data class Clouds(
            @Json(name = "all") val all: Int
        )

        @JsonClass(generateAdapter = true)
        data class Wind(
            @Json(name = "speed") val speed: Double,
            @Json(name = "deg") val degrees: Int,
            @Json(name = "gust") val gust: Double?
        )

        @JsonClass(generateAdapter = true)
        data class Precipitation(
            @Json(name = "3h") val threeHourVolume: Double?
        )

        @JsonClass(generateAdapter = true)
        data class Sys(
            @Json(name = "pod") val partOfDay: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String,
        @Json(name = "coord") val coordinates: Coordinates,
        @Json(name = "country") val country: String,
        @Json(name = "population") val population: Int?,
        @Json(name = "timezone") val timezone: Int,
        @Json(name = "sunrise") val sunrise: Long?,
        @Json(name = "sunset") val sunset: Long?
    ) {
        @JsonClass(generateAdapter = true)
        data class Coordinates(
            @Json(name = "lat") val latitude: Double,
            @Json(name = "lon") val longitude: Double
        )
    }
}
