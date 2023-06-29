package com.example.weatherapp.domain.usecase.forecast

import com.example.weatherapp.base.BaseSuspendUseCase
import com.example.weatherapp.data.entity.forecast.WeatherResponse
import com.example.weatherapp.domain.repositories.weather.WeatherRepository
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) : BaseSuspendUseCase<ForecastUseCase.Input, WeatherResponse>() {

    override suspend fun create(input: Input): WeatherResponse =
        repository.getForecast(
            lat = input.lat,
            lon = input.lon
        )

    data class Input(val lat: Double, val lon: Double)
}