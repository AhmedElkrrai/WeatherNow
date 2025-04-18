package com.example.forecast.domain.usecases

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.forecast.domain.ForecastRepository
import com.example.forecast.domain.entities.WeatherForecast

class GetWeatherForecastUseCase(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(city: City): Result<WeatherForecast, DataError> {
        return repository.getWeatherForecast(city)
    }
}
