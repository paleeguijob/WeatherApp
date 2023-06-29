package com.example.weatherapp.ui.landing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.base.model.toBaseCommonError
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.domain.mapper.WeatherMapper
import com.example.weatherapp.ui.landing.model.LandingWeatherUi
import com.example.weatherapp.domain.usecase.currentweather.CurrentWeatherUseCase
import com.example.weatherapp.domain.usecase.geo.GeoUseCase
import com.example.weatherapp.util.kelvinToCelsius
import com.example.weatherapp.util.kelvinToFahrenheit
import com.example.weatherapp.util.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingWeatherFragmentViewModel @Inject constructor(
    private val weatherUseCase: CurrentWeatherUseCase,
    private val geoUseCase: GeoUseCase,
    private val weatherMapper: WeatherMapper
) : ViewModel() {

    val landingWeatherUi = MutableLiveData<LandingWeatherUi>()
    val isCelsius = MutableLiveData<Boolean>()

    fun fetchGeoThenFetchWeather(cityName: String) {
        landingWeatherUi.value = LandingWeatherUi(LandingWeatherUi.UiState.Loading)

        viewModelScope.launch {
            geoUseCase.execute(GeoUseCase.Input(cityName))
                .onSuccess {
                    fetchCurrentWeather(it.first())
                }
                .onFailure {
                    landingWeatherUi.value =
                        LandingWeatherUi(LandingWeatherUi.UiState.Error(it.toBaseCommonError()))
                }
        }
    }

    private fun fetchCurrentWeather(geoResponseItem: GeoResponseItem) {
        val lat = geoResponseItem.lat.orZero()
        val lon = geoResponseItem.lon.orZero()

        viewModelScope.launch {
            weatherUseCase.execute(CurrentWeatherUseCase.Input(lat = lat, lon = lon))
                .onSuccess { response ->
                    landingWeatherUi.value = LandingWeatherUi(
                        LandingWeatherUi.UiState.Success(
                            weatherMapper.transformCurrentWeather(response, geoResponseItem)
                        )
                    )
                }.onFailure {
                    landingWeatherUi.value = LandingWeatherUi(
                        LandingWeatherUi.UiState.Error(it.toBaseCommonError())
                    )
                }
        }
    }

    fun switchTemp(isCelsius: Boolean, temp: Double): String {
        return when(isCelsius){
            true -> temp.kelvinToCelsius().toString()
            false -> temp.kelvinToFahrenheit().toString()
        }
    }

    companion object {
        const val CITY_DEFAULT = "bangkok"
    }
}