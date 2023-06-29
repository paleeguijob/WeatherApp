package com.example.weatherapp.domain.usecase.geo

import com.example.weatherapp.base.BaseSuspendUseCase
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.domain.repositories.weather.WeatherRepository
import javax.inject.Inject

class GeoUseCase @Inject constructor(
    private val repository: WeatherRepository
) : BaseSuspendUseCase<GeoUseCase.Input, List<GeoResponseItem>>() {

    override suspend fun create(input: Input): List<GeoResponseItem> =
        repository.getGeo(input.cityName)

    data class Input(val cityName: String)
}