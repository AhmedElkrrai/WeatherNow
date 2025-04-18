package com.example.cityinput.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.core.presentation.ObserveEvents
import com.example.core.presentation.Screen
import com.example.core.presentation.getMessage

@Composable
fun CityInputScreenRoot(
    navController: NavHostController,
    viewModel: CityViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveEvents(viewModel.event) { event ->
        when (event) {
            is CityEvent.ShowError -> {
                Toast.makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT).show()
            }

            is CityEvent.Navigate -> {
                state?.cityName?.let {
                    navController.navigate(Screen.Weather.route)
                }
            }
        }
    }

    CityInputScreen(
        currentCity = state,
        onCitySelected = { city ->
            viewModel.selectCity(city)
        }
    )
}
