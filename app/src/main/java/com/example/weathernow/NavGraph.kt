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
import com.example.forecast.presentation.ForecastScreenRoot

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Screen,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Screen.CityInput.route) {
            CityInputScreenRoot(navController)
        }

        composable(
            route = Screen.Weather.route,
        ) {
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
                navController = navController,
                city = backStackEntry.arguments?.getString(CITY) ?: "",
            )
        }
    }
}
