package com.example.currentweather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.core.domain.onError
import com.example.core.domain.onSuccess
import com.example.core.presentation.Screen
import com.example.currentweather.domain.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getSelectedCity: GetSelectedCityUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherViewState())
    val state: StateFlow<WeatherViewState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<WeatherEvent>()
    val event: MutableSharedFlow<WeatherEvent> = _event

    fun getCurrentWeather() {
        viewModelScope.launch {
            getSelectedCity.invoke()
                .onSuccess { city ->
                    _state.update { state -> state.copy(isLoading = true) }
                    val result = getCurrentWeatherUseCase(city)
                    result.onSuccess { weather ->
                        _state.update { state ->
                            state.copy(
                                city = city,
                                currentWeather = weather,
                                isLoading = false
                            )
                        }
                    }.onError { weatherError ->
                        _state.update { state -> state.copy(isLoading = false) }
                        _event.emit(WeatherEvent.ShowError(weatherError))
                    }
                }.onError { cityError ->
                    _event.emit(WeatherEvent.ShowError(cityError))
                }
        }
    }

    fun navigateToForecast() {
        viewModelScope.launch {
            state.value.city?.name?.let {
                _event.emit(
                    WeatherEvent.Navigate(Screen.Forecast.createRoute(it))
                )
            }
        }
    }

    fun navigateToCityInput() {
        viewModelScope.launch {
            _event.emit(WeatherEvent.Navigate(Screen.CityInput.route))
        }
    }
}
