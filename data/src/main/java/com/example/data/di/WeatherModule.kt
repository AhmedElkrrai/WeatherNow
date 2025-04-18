package com.example.data.di

import com.example.currentweather.domain.WeatherRepository
import com.example.data.source.WeatherRepositoryImpl
import com.example.data.source.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: WeatherApiService
    ): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }
}
