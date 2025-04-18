package com.example.data.mappers

import com.example.data.source.remote.entities.ConditionType
import com.example.data.source.remote.entities.Coordinates
import com.example.data.source.remote.entities.CurrentWeather
import com.example.data.source.remote.dto.CurrentWeatherDto
import com.example.data.source.remote.dto.ForecastResponseDto
import com.example.data.source.remote.dto.WeatherDto
import com.example.data.source.remote.entities.WeatherForecast
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
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
                val mainItem = items.maxByOrNull { it.main.temp } ?: items.first()
                WeatherForecast.DailyForecast(
                    date = date,
                    temperature = WeatherForecast.DailyForecast.Temperature(
                        day = items.maxOf { it.main.temp },
                        min = items.minOf { it.main.tempMin },
                        max = items.maxOf { it.main.tempMax },
                        night = items.findNightTemp(),
                        evening = items.findEveningTemp(),
                        morning = items.findMorningTemp()
                    ),
                    condition = WeatherForecast.DailyForecast.WeatherCondition(
                        type = mainItem.weather.firstOrNull()?.toConditionType()
                            ?: ConditionType.UNKNOWN,
                        description = mainItem.weather.firstOrNull()?.description ?: "",
                        iconCode = mainItem.weather.firstOrNull()?.icon ?: ""
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
                    pressure = mainItem.main.pressure
                )
            }
    )
}

private fun WeatherDto.toConditionType(): ConditionType {
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

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

private fun List<ForecastResponseDto.ForecastItem>.findTimeBasedTemp(
    vararg hourRanges: IntRange
): Double? {
    return this.firstOrNull { item ->
        val hour = item.dateTime.toLocalDateTime().hour
        hourRanges.any { range -> hour in range }
    }?.main?.temp
}

private fun List<ForecastResponseDto.ForecastItem>.findNightTemp(): Double {
    return findTimeBasedTemp(0..5, 21..23)
        ?: this.minByOrNull { it.main.temp }?.main?.temp ?: 0.0
}

private fun List<ForecastResponseDto.ForecastItem>.findEveningTemp(): Double {
    return findTimeBasedTemp(18..20)
        ?: this.maxByOrNull { it.main.temp }?.main?.temp ?: 0.0
}

private fun List<ForecastResponseDto.ForecastItem>.findMorningTemp(): Double {
    return findTimeBasedTemp(6..11)
        ?: this.firstOrNull()?.main?.temp ?: 0.0
}
