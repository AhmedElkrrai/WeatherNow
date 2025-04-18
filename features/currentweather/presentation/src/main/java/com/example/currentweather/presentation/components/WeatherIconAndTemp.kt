package com.example.currentweather.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WeatherIconAndTemp(iconCode: String, temp: Double, description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@4x.png"
        Log.d("WeatherIconAndTemp", "Icon URL: $iconUrl")
        AsyncImage(
            model = iconUrl,
            contentDescription = description,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "${temp}°C",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = description.replaceFirstChar { it.uppercaseChar() },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}