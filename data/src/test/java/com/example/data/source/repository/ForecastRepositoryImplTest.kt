package com.example.data.source.repository

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.data.source.remote.OneCallApiService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ForecastRepositoryImplTest {

    private val remoteSource: OneCallApiService = mockk()
    private lateinit var repository: ForecastRepositoryImpl

    @Before
    fun setUp() {
        repository = ForecastRepositoryImpl(remoteSource)
    }

    @Test
    fun `getDailyForecast returns Error when API call throws exception`() = runTest {
        // Arrange
        val city = City.CAIRO
        coEvery {
            remoteSource.getDailyForecast(
                any(),
                any()
            )
        } throws RuntimeException("Network error")

        // Act
        val result = repository.getDailyForecast(city)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals(DataError.FailedToRetrieveData, (result as Result.Error).error)
    }
}
