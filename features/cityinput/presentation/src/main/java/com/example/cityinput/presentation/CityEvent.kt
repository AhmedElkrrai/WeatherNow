package com.example.cityinput.presentation

import com.example.core.domain.DataError

sealed class CityEvent {
    data object Navigate : CityEvent()
    data class ShowError(val error: DataError) : CityEvent()
}