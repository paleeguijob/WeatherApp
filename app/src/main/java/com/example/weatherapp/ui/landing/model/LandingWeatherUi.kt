package com.example.weatherapp.ui.landing.model

import android.os.Parcelable
import com.example.weatherapp.base.model.BaseCommonError
import kotlinx.parcelize.Parcelize

data class LandingWeatherUi(val uiState: UiState = UiState.Loading) {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: WeatherUi) : UiState()
        data class Error(val error: BaseCommonError) : UiState()
    }

    @Parcelize
    data class WeatherUi(
        val cityName: String,
        val lat: Double,
        val lon: Double,
        var isCelsius: Boolean? = true,
        val temp: Double,
        val humidity: Int,
        val icon: String
    ) : Parcelable
}