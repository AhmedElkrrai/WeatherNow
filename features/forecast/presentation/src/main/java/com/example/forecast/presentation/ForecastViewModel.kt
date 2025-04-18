package com.example.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.onError
import com.example.core.domain.onSuccess
import com.example.forecast.domain.usecases.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecast: GetWeatherForecastUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val state: StateFlow<ForecastState> = _state.asStateFlow()

    private val _event = Channel<ForecastEvent>()
    val event = _event.receiveAsFlow()

    private var currentCity: City? = null

    fun onAction(action: ForecastAction) {
        when (action) {
            is ForecastAction.LoadForecast -> loadForecast(action.city)
            is ForecastAction.Retry -> currentCity?.let { loadForecast(it) }
        }
    }

    private fun loadForecast(city: City) {
        currentCity = city
        viewModelScope.launch {
            _state.value = ForecastState.Loading

            getForecast(city)
                .onSuccess { forecast ->
                    _state.value = ForecastState.Success(
                        city = city,
                        forecast = forecast
                    )
                }
                .onError { error ->
                    _state.value = ForecastState.Error(
                        error = error as? DataError ?: DataError.GeneralError,
                        city = city
                    )
                    _event.send(ForecastEvent.ShowError(error))
                }
        }
    }
}
