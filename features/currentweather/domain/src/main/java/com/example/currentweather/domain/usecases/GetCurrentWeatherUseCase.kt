package com.example.currentweather.domain.usecases

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.currentweather.domain.WeatherRepository
import com.example.currentweather.domain.entities.CurrentWeather

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: City): Result<CurrentWeather, DataError> {
        return repository.getCurrentWeather(city)
    }
}
