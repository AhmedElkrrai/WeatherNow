package com.example.forecast.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.presentation.CelestialBlue
import com.example.forecast.domain.entities.WeatherForecast

@Composable
fun ForecastCard(forecast: WeatherForecast.DailyForecast) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(CelestialBlue)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = forecast.date.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forecast.condition.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "Min: ${forecast.temperature.min}°C | Max: ${forecast.temperature.max}°C",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            val url = "https://openweathermap.org/img/wn/${forecast.condition.iconCode}@2x.png"
            Log.d("ForecastCard", "Taggs - Image URL: $url")
            AsyncImage(
                model = url,
                contentDescription = forecast.condition.description,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}
