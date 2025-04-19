package com.example.weathernow

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cityinput.presentation.components.CityInputScreenRoot
import com.example.core.presentation.CITY
import com.example.core.presentation.Screen
import com.example.currentweather.presentation.components.WeatherScreenRoot
import com.example.forecast.presentation.components.ForecastScreenRoot

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.CityInput.route) {
            CityInputScreenRoot(navController)
        }

        composable(Screen.Weather.route) {
            WeatherScreenRoot(
                navController = navController,
            )
        }

        composable(
            route = Screen.Forecast.route,
            arguments = listOf(
                navArgument(CITY) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            ForecastScreenRoot(
                cityKey = backStackEntry.arguments?.getString(CITY) ?: "",
            )
        }
    }
}
