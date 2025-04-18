package com.example.currentweather.domain

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.currentweather.domain.entities.CurrentWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: City): Result<CurrentWeather, DataError>
}
