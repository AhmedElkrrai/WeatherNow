package com.example.cityinput.presentation

import app.cash.turbine.test
import com.example.cityinput.domain.usecase.CacheSelectedCityUseCase
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.core.domain.City
import com.example.core.domain.DataError
import com.example.core.domain.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
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
class CityViewModelTest {

    private lateinit var viewModel: CityViewModel

    private val cacheSelectedCity: CacheSelectedCityUseCase = mockk(relaxed = true)
    private val getSelectedCity: GetSelectedCityUseCase = mockk()

    private val testCity = City.CAIRO

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadSavedCity - success updates state`() = runTest {
        coEvery { getSelectedCity.invoke() } returns Result.Success(testCity)

        viewModel = CityViewModel(cacheSelectedCity, getSelectedCity)

        advanceUntilIdle()

        assertEquals(testCity, viewModel.state.value)
    }

    @Test
    fun `loadSavedCity - error emits ShowError event`() = runTest {
        val error = DataError.LocalError
        coEvery { getSelectedCity.invoke() } returns Result.Error(error)

        viewModel = CityViewModel(cacheSelectedCity, getSelectedCity)

        viewModel.event.test {
            val emitted = awaitItem()
            assert(emitted is CityEvent.ShowError && emitted.error == error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
