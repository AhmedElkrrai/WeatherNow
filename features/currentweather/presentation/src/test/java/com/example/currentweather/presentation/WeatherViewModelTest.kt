package com.example.currentweather.presentation

import app.cash.turbine.test
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import com.example.core.presentation.Screen
import com.example.currentweather.domain.entities.CurrentWeather
import com.example.currentweather.domain.usecases.GetCurrentWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var getSelectedCityUseCase: GetSelectedCityUseCase
    private lateinit var viewModel: WeatherViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        getCurrentWeatherUseCase = mockk()
        getSelectedCityUseCase = mockk()

        viewModel = WeatherViewModel(
            getCurrentWeatherUseCase,
            getSelectedCityUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCurrentWeather - success`() = runTest {
        val city = City.CAIRO
        val weather = CurrentWeather()

        coEvery { getSelectedCityUseCase.invoke() } returns Result.Success(city)
        coEvery { getCurrentWeatherUseCase.invoke(city) } returns Result.Success(weather)

        viewModel.getCurrentWeather()
        advanceUntilIdle()

        viewModel.state.test {
            val actual = awaitItem()
            assertEquals(city, actual.city)
            assertEquals(weather, actual.currentWeather)
            assertFalse(actual.isLoading)
        }
    }

    @Test
    fun `getCurrentWeather - error getting city emits ShowError`() = runTest {
        val error = DataError.LocalError
        coEvery { getSelectedCityUseCase.invoke() } returns Result.Error(error)

        viewModel.getCurrentWeather()

        viewModel.event.test {
            val actual = awaitItem()
            assertEquals(WeatherEvent.ShowError(error), actual)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `navigateToForecast emits Navigate if city is available`() = runTest {
        val city = City.CAIRO
        val weather = CurrentWeather()

        coEvery { getSelectedCityUseCase.invoke() } returns Result.Success(city)
        coEvery { getCurrentWeatherUseCase.invoke(city) } returns Result.Success(weather)

        viewModel.getCurrentWeather()
        advanceUntilIdle()

        viewModel.navigateToForecast()

        viewModel.event.test {
            val event = awaitItem()
            assertTrue(event is WeatherEvent.Navigate)
            assertTrue((event as WeatherEvent.Navigate).route.contains("Cairo"))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `navigateToCityInput emits correct navigation event`() = runTest {
        viewModel.navigateToCityInput()

        viewModel.event.test {
            val event = awaitItem()
            assertEquals(WeatherEvent.Navigate(Screen.CityInput.route), event)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
