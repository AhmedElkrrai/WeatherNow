package com.example.currentweather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currentweather.presentation.WeatherViewState
import java.time.format.DateTimeFormatter

@Composable
fun WeatherScreen(
    state: WeatherViewState,
    onForecastClick: () -> Unit,
    onCityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val weather = state.currentWeather
    val cityName = state.city?.cityName ?: "Unknown"

    if (weather == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = cityName,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = weather.timestamp.format(DateTimeFormatter.ofPattern("hh:mm a")),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        WeatherIconAndTemp(
            iconCode = weather.condition.iconCode,
            temp = weather.temperature.current,
            description = weather.condition.description
        )

        WeatherDetailRow(label = "Feels like", value = "${weather.temperature.feelsLike}°C")
        WeatherDetailRow(label = "Min / Max", value = "${weather.temperature.min}° / ${weather.temperature.max}°")
        WeatherDetailRow(label = "Wind", value = "${weather.wind.speed} m/s")
        WeatherDetailRow(label = "Humidity", value = "${weather.humidity}%")
        WeatherDetailRow(label = "Pressure", value = "${weather.pressure} hPa")
        WeatherDetailRow(label = "Visibility", value = "${weather.visibility} m")

        weather.sunrise?.let {
            WeatherDetailRow(label = "Sunrise", value = it.format(DateTimeFormatter.ofPattern("hh:mm a")))
        }

        weather.sunset?.let {
            WeatherDetailRow(label = "Sunset", value = it.format(DateTimeFormatter.ofPattern("hh:mm a")))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onCityClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Change City")
            }

            Button(
                onClick = onForecastClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("View Forecast")
            }
        }
    }
}
