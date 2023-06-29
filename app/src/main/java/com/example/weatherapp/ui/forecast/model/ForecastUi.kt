package com.example.weatherapp.ui.forecast.model

import com.example.weatherapp.base.model.BaseCommonError

data class ForecastUi(val uiState: UiState = UiState.Loading) {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: List<WeatherUi>) : UiState()
        data class Error(val error: BaseCommonError): UiState()
    }

    data class WeatherUi(
        val dayOfWeek: String,
        val time: String,
        val tempCelsius: Int,
        val icon: String
    )
}