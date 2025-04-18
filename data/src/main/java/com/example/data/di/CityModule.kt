package com.example.data.di

import com.example.cityinput.domain.CityRepository
import com.example.cityinput.domain.usecase.CacheSelectedCityUseCase
import com.example.cityinput.domain.usecase.GetSelectedCityUseCase
import com.example.data.source.repository.CityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CityBinder {
    @Singleton
    @Binds
    fun bindCityRepository(impl: CityRepositoryImpl): CityRepository
}

@Module
@InstallIn(SingletonComponent::class)
object CityModule {
    @Provides
    fun provideCacheSelectedCityUseCase(
        repository: CityRepository
    ): CacheSelectedCityUseCase {
        return CacheSelectedCityUseCase(repository)
    }

    @Provides
    fun provideGetSelectedCityUseCase(
        repository: CityRepository
    ): GetSelectedCityUseCase {
        return GetSelectedCityUseCase(repository)
    }
}
