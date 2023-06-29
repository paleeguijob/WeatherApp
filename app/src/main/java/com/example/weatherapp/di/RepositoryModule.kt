package com.example.weatherapp.di

import com.example.weatherapp.domain.repositories.weather.WeatherRepository
import com.example.weatherapp.domain.repositories.weather.WeatherRepositoryImpl
import com.example.weatherapp.domain.mapper.WeatherMapper
import com.example.weatherapp.domain.mapper.WeatherMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindWeatherMapper(mapper: WeatherMapperImpl): WeatherMapper
}