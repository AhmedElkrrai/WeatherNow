package com.example.forecast.presentation

import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.forecast.domain.entities.OneCallWeatherForecast
import com.example.forecast.domain.usecases.GetDailyForecastUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ForecastViewModelTest {

    private lateinit var getDailyForecastUseCase: GetDailyForecastUseCase
    private lateinit var viewModel: ForecastViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getDailyForecastUseCase = mockk()
        viewModel = ForecastViewModel(getDailyForecastUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onAction LoadForecast emits Loading then Success`() = runTest {
        // Arrange
        val city = City.CAIRO
        val forecast = mockk<OneCallWeatherForecast>()
        coEvery { getDailyForecastUseCase(city) } returns Result.Success(forecast)

        val states = mutableListOf<ForecastState>()
        val collectJob = launch(testDispatcher) { viewModel.state.toList(states) }

        // Act
        viewModel.onAction(ForecastAction.LoadForecast(city))
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(2, states.size)
        assertEquals(ForecastState.Loading, states[0])
        assertEquals(ForecastState.Success(city, forecast), states[1])

        coVerify { getDailyForecastUseCase(city) }

        collectJob.cancel()
    }

    @Test
    fun `onAction LoadForecast emits Loading then Error`() = runTest {
        // Arrange
        val city = City.CAIRO
        val error = DataError.FailedToRetrieveData
        coEvery { getDailyForecastUseCase(city) } returns Result.Error(error)

        val states = mutableListOf<ForecastState>()
        val collectJob = launch(testDispatcher) { viewModel.state.toList(states) }

        // Act
        viewModel.onAction(ForecastAction.LoadForecast(city))
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(2, states.size)
        assertEquals(ForecastState.Loading, states[0])
        assertEquals(ForecastState.Error(error, city), states[1])

        coVerify { getDailyForecastUseCase(city) }

        collectJob.cancel()
    }
}