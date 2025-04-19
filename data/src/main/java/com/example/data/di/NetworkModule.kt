package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.source.remote.OneCallApiService
import com.example.data.source.remote.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OneCallClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @WeatherClient
    fun provideWeatherOkHttpClient(): OkHttpClient {
        return createBaseOkHttpClient(BuildConfig.WEATHER_API_KEY)
    }

    @Provides
    @Singleton
    @OneCallClient
    fun provideOneCallOkHttpClient(): OkHttpClient {
        return createBaseOkHttpClient(BuildConfig.ONECALL_API_KEY)
    }

    private fun createBaseOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .url(
                        chain.request().url.newBuilder()
                            .addQueryParameter("appid", apiKey)
                            .addQueryParameter("units", "metric")
                            .build()
                    )
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    @WeatherClient
    fun provideWeatherRetrofit(
        @WeatherClient okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return createRetrofit(okHttpClient, moshi)
    }

    @Provides
    @Singleton
    @OneCallClient
    fun provideOneCallRetrofit(
        @OneCallClient okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return createRetrofit(okHttpClient, moshi)
    }

    private fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(
        @WeatherClient retrofit: Retrofit
    ): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOneCallApiService(
        @OneCallClient retrofit: Retrofit
    ): OneCallApiService {
        return retrofit.create(OneCallApiService::class.java)
    }
}
