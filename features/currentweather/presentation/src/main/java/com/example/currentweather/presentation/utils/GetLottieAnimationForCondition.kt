package com.example.currentweather.presentation.utils

import android.util.Log
import com.example.currentweather.presentation.R

fun getLottieAnimationForCondition(iconCode: String): Int {
    Log.d("WeatherScreen", "Icon code: $iconCode")
    return when (iconCode) {
        "01d", "01n" -> R.raw.sunny
        "02d", "02n" -> R.raw.partly_cloudy
        "03d", "03n", "04d", "04n" -> R.raw.cloudy
        "09d", "09n", "10d", "10n" -> R.raw.rainy
        "11d", "11n" -> R.raw.storm
        "13d", "13n" -> R.raw.snow
        "50d", "50n" -> R.raw.foggy
        else -> R.raw.sunny
    }
}
