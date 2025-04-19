package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.local.CityPreferenceManager
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CityRepositoryImplTest {

    private val cityPreferenceManager: CityPreferenceManager = mockk()
    private lateinit var repository: CityRepositoryImpl

    @Before
    fun setUp() {
        repository = CityRepositoryImpl(cityPreferenceManager)
    }

    @Test
    fun `cacheSelectedCity saves city using preference manager`() {
        // Arrange
        val city = City.CAIRO
        every { cityPreferenceManager.saveCity(city) } just Runs

        // Act
        repository.cacheSelectedCity(city)

        // Assert
        verify(exactly = 1) { cityPreferenceManager.saveCity(city) }
    }

    @Test
    fun `getSelectedCity returns Success when city exists`() {
        // Arrange
        val city = City.CAIRO
        every { cityPreferenceManager.getCity() } returns city

        // Act
        val result = repository.getSelectedCity()

        // Assert
        assertTrue(result is Result.Success)
        assertEquals(city, (result as Result.Success).data)
    }

    @Test
    fun `getSelectedCity returns Error when no city is found`() {
        // Arrange
        every { cityPreferenceManager.getCity() } returns null

        // Act
        val result = repository.getSelectedCity()

        // Assert
        assertTrue(result is Result.Error)
        assertEquals(DataError.LocalError, (result as Result.Error).error)
    }
}
