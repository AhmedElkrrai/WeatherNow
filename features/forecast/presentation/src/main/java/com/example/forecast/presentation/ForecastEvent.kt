package com.example.forecast.presentation

import com.example.core.domain.DataError

sealed interface ForecastEvent {
    data class ShowError(val error: DataError) : ForecastEvent
}