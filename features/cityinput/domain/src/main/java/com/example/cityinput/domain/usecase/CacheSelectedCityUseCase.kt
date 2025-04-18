package com.example.cityinput.domain.usecase

import com.example.cityinput.domain.CityRepository
import com.example.core.domain.City

class CacheSelectedCityUseCase(
    private val repository: CityRepository
) {
    operator fun invoke(city: City) {
        repository.cacheSelectedCity(city)
    }
}
