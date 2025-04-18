package com.example.core.presentation

const val CITY = "city"

sealed class Screen(val route: String) {
    data object CityInput : Screen("city_input_screen")
    data object Weather : Screen("weather_screen")

    data object Forecast : Screen("forecast_screen/{$CITY}") {
        fun createRoute(city: String): String {
            return "forecast_screen/$city"
        }
    }
}
