package com.example.weatherapp.domain.usecase.currentweather

import com.example.weatherapp.base.BaseSuspendUseCase
import com.example.weatherapp.data.entity.currentweather.CurrentWeather
import com.example.weatherapp.domain.repositories.weather.WeatherRepository
import javax.inject.Inject

class CurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
): BaseSuspendUseCase<CurrentWeatherUseCase.Input, CurrentWeather>() {

    override suspend fun create(input: Input): CurrentWeather =
        repository.getCurrentWeather(
            lat = input.lat,
            lon = input.lon
        )

    data class Input(val lat: Double, val lon: Double)
}