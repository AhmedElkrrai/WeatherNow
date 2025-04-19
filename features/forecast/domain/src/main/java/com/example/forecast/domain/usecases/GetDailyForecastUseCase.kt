package com.example.forecast.domain.usecases

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.forecast.domain.ForecastRepository
import com.example.forecast.domain.entities.OneCallWeatherForecast

class GetDailyForecastUseCase(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(city: City): Result<OneCallWeatherForecast, DataError> {
        return repository.getDailyForecast(city)
    }
}
