package com.example.weather_utils

fun getEmojiForCondition(iconCode: String): String {
    return when (iconCode) {
        "01d", "01n" -> "☀️"   // Clear sky
        "02d", "02n" -> "⛅"  // Few clouds
        "03d", "03n" -> "☁️"   // Scattered clouds
        "04d", "04n" -> "☁️"   // Broken clouds
        "09d", "09n" -> "🌧️"  // Shower rain
        "10d", "10n" -> "🌦️"  // Rain
        "11d", "11n" -> "⛈️"   // Thunderstorm
        "13d", "13n" -> "❄️"   // Snow
        "50d", "50n" -> "🌫️"   // Mist
        else -> "🌡️"          // Default/unknown
    }
}
