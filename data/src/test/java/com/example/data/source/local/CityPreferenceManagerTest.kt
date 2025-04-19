package com.example.data.source.local

import android.content.SharedPreferences
import com.example.core.domain.City
import io.mockk.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CityPreferenceManagerTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var cityPreferenceManager: CityPreferenceManager

    @Before
    fun setUp() {
        sharedPreferences = mockk()
        editor = mockk()
        cityPreferenceManager = CityPreferenceManager(sharedPreferences)
    }

    @Test
    fun `saveCity saves city name to SharedPreferences`() {
        val city = City.CAIRO

        every { sharedPreferences.edit() } returns editor
        every { editor.putString("selected_city_name", city.cityName) } returns editor
        every { editor.apply() } just Runs

        cityPreferenceManager.saveCity(city)

        verify {
            sharedPreferences.edit()
            editor.putString("selected_city_name", city.cityName)
            editor.apply()
        }
    }

    @Test
    fun `getCity returns City when valid name is found`() {
        every { sharedPreferences.getString("selected_city_name", null) } returns "Cairo"

        val result = cityPreferenceManager.getCity()

        assertEquals(City.CAIRO, result)
    }

    @Test
    fun `getCity returns null when SharedPreferences has no value`() {
        every { sharedPreferences.getString("selected_city_name", null) } returns null

        val result = cityPreferenceManager.getCity()

        assertNull(result)
    }

    @Test
    fun `getCity returns null when city name is invalid`() {
        every { sharedPreferences.getString("selected_city_name", null) } returns "Atlantis"

        val result = cityPreferenceManager.getCity()

        assertNull(result)
    }
}
