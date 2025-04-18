package com.example.data.source.local

import android.content.SharedPreferences
import com.example.cityinput.domain.entities.City
import javax.inject.Inject

class CityPreferenceManager @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {
    fun saveCity(city: City) {
        sharedPrefs.edit()
            .putString("selected_city_name", city.cityName)
            .apply()
    }

    fun getCity(): City? {
        val name = sharedPrefs.getString("selected_city_name", null) ?: return null

        return City.getCityByName(name)
    }
}
