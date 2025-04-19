package com.example.weathernow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.core.domain.onError
import com.example.core.domain.onSuccess
import com.example.core.presentation.Screen
import com.example.core.presentation.WeatherTheme
import com.example.core.presentation.enableStickyImmersiveMode
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getSelectedCityUseCase: GetSelectedCityUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var startDestination = Screen.CityInput.route
        getSelectedCityUseCase.invoke()
            .onSuccess {
                startDestination = Screen.Weather.route
            }

        setContent {
            WeatherTheme {
                val rootNavController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(
                        navController = rootNavController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        enableStickyImmersiveMode()
    }
}
