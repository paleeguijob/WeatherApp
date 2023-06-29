package com.example.weatherapp.domain.repositories.weather

import com.example.weatherapp.data.entity.currentweather.CurrentWeather
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.data.entity.forecast.WeatherResponse

interface WeatherRepository {

    suspend fun getGeo(cityName: String): List<GeoResponseItem>
    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeather
    suspend fun getForecast(lat: Double, lon: Double): WeatherResponse
}