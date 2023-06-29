package com.example.weatherapp.domain.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.data.entity.currentweather.CurrentWeather
import com.example.weatherapp.data.entity.forecast.WeatherResponse
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.ui.forecast.model.ForecastUi
import com.example.weatherapp.ui.landing.model.LandingWeatherUi
import com.example.weatherapp.util.BaseUtil.BASE_IMAGE_WEATHER_URL
import com.example.weatherapp.util.kelvinToCelsius
import com.example.weatherapp.util.orZero
import com.example.weatherapp.util.toLocalDateTime
import javax.inject.Inject

interface WeatherMapper {

    fun transformCurrentWeather(
        data: CurrentWeather,
        geoResponseItem: GeoResponseItem
    ): LandingWeatherUi.WeatherUi

    fun transformForecast(data: WeatherResponse): List<ForecastUi.WeatherUi>
}

class WeatherMapperImpl @Inject constructor() : WeatherMapper {

    override fun transformCurrentWeather(
        data: CurrentWeather,
        geoResponseItem: GeoResponseItem
    ): LandingWeatherUi.WeatherUi {
        return LandingWeatherUi.WeatherUi(
            cityName = geoResponseItem.name.orEmpty(),
            lat = geoResponseItem.lat.orZero(),
            lon = geoResponseItem.lon.orZero(),
            temp = data.main?.temp.orZero(),
            humidity = data.main?.humidity.orZero(),
            icon = "${BASE_IMAGE_WEATHER_URL}${data.weather?.first()?.icon.orEmpty()}@2x.png"
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun transformForecast(data: WeatherResponse): List<ForecastUi.WeatherUi> {
        return data.list?.subList(0, 6)?.map {
            ForecastUi.WeatherUi(
                tempCelsius = it.main?.temp?.kelvinToCelsius().orZero(),
                dayOfWeek = transformDayOfWeek(it.dt_txt.orEmpty()),
                time = transformFormatTime(it.dt_txt.orEmpty()),
                icon = "${BASE_IMAGE_WEATHER_URL}${it.weather?.first()?.icon.orEmpty()}@2x.png"
            )
        } ?: listOf()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun transformDayOfWeek(dateTime: String): String {
        val date = dateTime.split(" ")
        return date[0].toLocalDateTime().dayOfWeek.toString()
    }

    private fun transformFormatTime(dateTime: String) : String{
        val date = dateTime.split(" ")
        return date[1].subSequence(0,5).toString()
    }
}