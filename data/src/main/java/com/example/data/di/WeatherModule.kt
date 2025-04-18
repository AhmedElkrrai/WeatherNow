package com.example.data.di

import com.example.currentweather.domain.WeatherRepository
import com.example.currentweather.domain.usecases.GetCurrentWeatherUseCase
import com.example.data.source.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherBinder {
    @Singleton
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    fun provideGetCurrentWeatherUseCase(
        repository: WeatherRepository
    ): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repository)
    }
}
