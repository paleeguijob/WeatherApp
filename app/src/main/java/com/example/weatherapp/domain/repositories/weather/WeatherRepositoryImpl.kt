package com.example.weatherapp.domain.repositories.weather

import com.example.weatherapp.base.model.toDataOrThrow
import com.example.weatherapp.data.entity.currentweather.CurrentWeather
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.data.entity.forecast.WeatherResponse
import com.example.weatherapp.data.service.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getGeo(cityName: String): List<GeoResponseItem> =
        weatherService.getGeo(q = cityName).toDataOrThrow()

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeather =
        weatherService.getCurrentWeather(lat, lon).toDataOrThrow()

    override suspend fun getForecast(lat: Double, lon: Double): WeatherResponse =
        weatherService.getForecast(lat, lon).toDataOrThrow()
}