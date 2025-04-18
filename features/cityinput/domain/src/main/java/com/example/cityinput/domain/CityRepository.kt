package com.example.cityinput.domain

import com.example.cityinput.domain.entities.City

interface CityRepository {
    fun cacheSelectedCity(city: City)
    fun getSelectedCity(): City
}
