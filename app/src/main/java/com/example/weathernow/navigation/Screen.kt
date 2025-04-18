package com.example.weathernow.navigation

const val CITY = "city"

sealed class Screen(val route: String) {
    data object CityInput : Screen("city_input_screen")
    data object Weather : Screen("weather_screen/{$CITY}") {
        fun createRoute(city: String): String {
            return "weather_screen/$city"
        }
    }

    data object Forecast : Screen("forecast_screen/{$CITY}") {
        fun createRoute(city: String): String {
            return "forecast_screen/$city"
        }
    }
}
