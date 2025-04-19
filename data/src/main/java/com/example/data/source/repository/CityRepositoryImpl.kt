package com.example.data.source.repository

import com.example.cityinput.domain.CityRepository
import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.local.CityPreferenceManager
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val localSource: CityPreferenceManager,
) : CityRepository {

    override fun cacheSelectedCity(city: City) {
        localSource.saveCity(city)
    }

    override fun getSelectedCity(): Result<City, DataError> {
        try {
            val city = localSource.getCity() ?: throw Exception()
            return Result.Success(city)
        } catch (e: Exception) {
            return Result.Error(DataError.LocalError)
        }
    }
}
