package com.example.cityinput.domain

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result

interface CityRepository {
    fun cacheSelectedCity(city: City)
    fun getSelectedCity(): Result<City, DataError>
}
