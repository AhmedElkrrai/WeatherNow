package com.example.data.di

import com.example.data.source.repository.ForecastRepositoryImpl
import com.example.forecast.domain.ForecastRepository
import com.example.forecast.domain.usecases.GetWeatherForecastUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ForecastBinder {
    @Singleton
    @Binds
    fun bindForecastRepository(impl: ForecastRepositoryImpl): ForecastRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {
    @Provides
    fun provideGetWeatherForecastUseCase(
        repository: ForecastRepository
    ): GetWeatherForecastUseCase {
        return GetWeatherForecastUseCase(repository)
    }
}
