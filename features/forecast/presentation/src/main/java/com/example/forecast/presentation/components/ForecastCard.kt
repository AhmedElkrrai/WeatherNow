package com.example.forecast.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.CelestialBlue
import com.example.core.presentation.DeepSkyBlue
import com.example.forecast.domain.entities.OneCallWeatherForecast
import com.example.weather_utils.getEmojiForCondition
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ForecastCard(
    date: LocalDate,
    temperature: OneCallWeatherForecast.Temperature,
    weather: OneCallWeatherForecast.Weather,
    humidity: Int,
    wind: OneCallWeatherForecast.Wind
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(CelestialBlue, DeepSkyBlue)))
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = date.format(DateTimeFormatter.ofPattern("EEE, MMM d")),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = weather.description.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        TemperatureItem("Day", temperature.day)
                        TemperatureItem("High", temperature.max)
                        TemperatureItem("Low", temperature.min)
                        TemperatureItem("Night", temperature.night)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "🌡️ ${temperature.min}° / ${temperature.max}°",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = "💧 ${humidity}%  |  💨 ${wind.speed} km/h",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getEmojiForCondition(weather.icon),
                        fontSize = 32.sp
                    )
                }
            }
        }
    }
}
