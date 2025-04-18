package com.example.cityinput.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityinput.domain.entities.City
import com.example.cityinput.domain.usecase.CacheSelectedCityUseCase
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.core.domain.onError
import com.example.core.domain.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val cacheSelectedCity: CacheSelectedCityUseCase,
    private val getSelectedCity: GetSelectedCityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<City?>(null)
    val state: StateFlow<City?> = _state

    private val _event = MutableSharedFlow<CityEvent>()
    val event: MutableSharedFlow<CityEvent> = _event

    init {
        loadSavedCity()
    }

    fun selectCity(city: City) {
        viewModelScope.launch {
            cacheSelectedCity.invoke(city)
            _state.value = city
            _event.emit(CityEvent.Navigate)
        }
    }

    private fun loadSavedCity() {
        viewModelScope.launch {
            getSelectedCity.invoke()
                .onSuccess {
                    _state.value = it
                }
                .onError {
                    _event.emit(CityEvent.ShowError(it))
                }
        }
    }
}
