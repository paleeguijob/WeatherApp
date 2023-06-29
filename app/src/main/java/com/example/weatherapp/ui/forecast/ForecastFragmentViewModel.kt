package com.example.weatherapp.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.base.model.toBaseCommonError
import com.example.weatherapp.domain.mapper.WeatherMapper
import com.example.weatherapp.ui.forecast.model.ForecastUi
import com.example.weatherapp.domain.usecase.forecast.ForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastFragmentViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase,
    private val weatherMapper: WeatherMapper
) : ViewModel() {

    val forecastUi = MutableLiveData<ForecastUi>()

    fun fetchForecastData(lat: Double, lon: Double) {
        forecastUi.value = ForecastUi(ForecastUi.UiState.Loading)

        viewModelScope.launch {
            forecastUseCase.execute(ForecastUseCase.Input(lat, lon))
                .onSuccess {
                    forecastUi.value = ForecastUi(
                        ForecastUi.UiState.Success(weatherMapper.transformForecast(it))
                    )
                }
                .onFailure {
                    forecastUi.value = ForecastUi(
                        ForecastUi.UiState.Error(it.toBaseCommonError())
                    )
                }
        }
    }
}