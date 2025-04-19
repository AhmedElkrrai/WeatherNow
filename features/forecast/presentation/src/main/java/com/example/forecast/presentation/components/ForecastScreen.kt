package com.example.forecast.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.presentation.DeepSkyBlue
import com.example.core.presentation.LightSkyBlue
import com.example.forecast.presentation.ForecastState

@Composable
fun ForecastScreen(
    state: ForecastState,
    modifier: Modifier = Modifier,
) {
    if (state !is ForecastState.Success) return

    val forecast = state.forecast

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(DeepSkyBlue, LightSkyBlue)))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = state.city.cityName,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Text(
                text = "Timezone: ${forecast.timezone}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        itemsIndexed(forecast.daily) { _, day ->
            ForecastCard(
                date = day.date,
                temperature = day.temperature,
                weather = day.weather,
                humidity = day.humidity,
                wind = day.wind,
            )
        }
    }
}
