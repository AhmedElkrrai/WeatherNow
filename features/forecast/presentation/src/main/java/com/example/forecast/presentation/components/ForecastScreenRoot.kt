package com.example.forecast.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.domain.City
import com.example.core.presentation.ErrorScreen
import com.example.core.presentation.LoadingScreen
import com.example.core.presentation.ObserveEvents
import com.example.core.presentation.getMessage
import com.example.forecast.presentation.ForecastAction
import com.example.forecast.presentation.ForecastEvent
import com.example.forecast.presentation.ForecastState
import com.example.forecast.presentation.ForecastViewModel

@Composable
fun ForecastScreenRoot(
    cityKey: String,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(cityKey) {
        City.getCityByName(cityKey)?.let { city ->
            viewModel.onAction(ForecastAction.LoadForecast(city))
        }
    }

    ObserveEvents(viewModel.event) { event ->
        when (event) {
            is ForecastEvent.ShowError -> {
                Toast.makeText(
                    context,
                    event.error.getMessage(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    when (val currentState = state) {
        is ForecastState.Loading -> LoadingScreen()
        is ForecastState.Error -> ErrorScreen(
            error = currentState.error,
            onRetry = { viewModel.onAction(ForecastAction.Retry) }
        )

        is ForecastState.Success -> ForecastScreen(currentState)
    }
}
