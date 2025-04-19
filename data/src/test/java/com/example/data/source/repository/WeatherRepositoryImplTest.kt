package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.WeatherApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    private lateinit var apiService: WeatherApiService
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        repository = WeatherRepositoryImpl(apiService)
    }

    @Test
    fun `getCurrentWeather returns Error when API call fails`() = runTest {
        // Given
        val city = City.CAIRO

        coEvery {
            apiService.getCurrentWeather(
                latitude = city.latitude,
                longitude = city.longitude
            )
        } throws RuntimeException("Network error")

        // When
        val result = repository.getCurrentWeather(city)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(DataError.FailedToRetrieveData, (result as Result.Error).error)
    }
}
