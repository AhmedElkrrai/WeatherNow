package com.example.cityinput.domain.usecase

import com.example.cityinput.domain.CityRepository
import com.example.cityinput.domain.entities.City
import com.example.core.domain.DataError
import com.example.core.domain.Result

class GetSelectedCityUseCase(
    private val repository: CityRepository
) {
    operator fun invoke(): Result<City, DataError> {
        return repository.getSelectedCity()
    }
}
