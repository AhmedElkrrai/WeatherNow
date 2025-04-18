package com.example.currentweather.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core.presentation.ObserveEvents
import com.example.core.presentation.getMessage

@Composable
fun WeatherScreenRoot(
    navController: NavHostController,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getCurrentWeather()
    }

    ObserveEvents(viewModel.event) { event ->
        when (event) {
            is WeatherEvent.ShowError -> {
                Toast.makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT).show()
            }

            is WeatherEvent.Navigate -> {
                state.city?.name?.let {
                    navController.navigate(event.route)
                }
            }
        }
    }

    WeatherScreen(
        state = state,
        onForecastClick = {
            viewModel.navigateToForecast()
        },
        onCityClick = {
            viewModel.navigateToCityInput()
        }
    )
}
