package com.example.currentweather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.presentation.DeepSkyBlue
import com.example.core.presentation.LightSkyBlue
import com.example.currentweather.presentation.WeatherViewState
import com.example.currentweather.presentation.utils.getLottieAnimationForCondition
import java.time.LocalDate

@Composable
fun WeatherScreen(
    state: WeatherViewState,
    onForecastClick: () -> Unit,
    onCityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val weather = state.currentWeather
    val city = state.city

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(DeepSkyBlue, LightSkyBlue)
                )
            )
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // City and date
            Text(
                text = city?.cityName ?: "Unknown City",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = LocalDate.now().toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            // Lottie weather animation
            weather?.condition?.iconCode?.let { icon ->
                val lottieRes = getLottieAnimationForCondition(icon)
                LottieAnimation(
                    composition = rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes)).value,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            // Temperature
            weather?.temperature?.let {
                Text(
                    text = "${it.current.toInt()}°C",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White
                )
                Text(
                    text = "Feels like ${it.feelsLike.toInt()}°C",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

            // Condition description
            weather?.condition?.description?.let {
                Text(
                    text = it.replaceFirstChar { c -> c.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            // Weather details
            weather?.let {
                WeatherDetailRow(label = "Wind", value = "${it.wind.speed} m/s")
                WeatherDetailRow(label = "Humidity", value = "${it.humidity}%")
                WeatherDetailRow(label = "Pressure", value = "${it.pressure} hPa")
                WeatherDetailRow(label = "Visibility", value = "${it.visibility} m")
            }

            Spacer(modifier = Modifier.weight(1f))

            // Actions
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onForecastClick,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text("Forecast")
                }

                OutlinedButton(
                    onClick = onCityClick,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text("Change City")
                }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
