package com.example.weathernow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cityinput.presentation.CityInputScreenRoot
import com.example.currentweather.presentation.WeatherScreenRoot
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
            arguments = listOf(
                navArgument(CITY) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            WeatherScreenRoot(
                navController = navController,
                city = backStackEntry.arguments?.getString(CITY) ?: "",
                backStackEntry = backStackEntry
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
                backStackEntry = backStackEntry
            )
        }
    }
}
