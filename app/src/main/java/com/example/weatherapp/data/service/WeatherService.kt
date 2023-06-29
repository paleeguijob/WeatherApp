package com.example.weatherapp.data.service

import com.example.weatherapp.base.model.BaseCommonError
import com.example.weatherapp.base.model.NetworkResponse
import com.example.weatherapp.data.entity.currentweather.CurrentWeather
import com.example.weatherapp.data.entity.geo.GeoResponseItem
import com.example.weatherapp.data.entity.forecast.WeatherResponse
import com.example.weatherapp.util.BaseUtil
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("geo/1.0/direct")
    suspend fun getGeo(
        @Query("q") q: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") key:String = BaseUtil.KEY_API
    ):NetworkResponse<List<GeoResponseItem>, BaseCommonError>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String = BaseUtil.KEY_API
    ):NetworkResponse<CurrentWeather, BaseCommonError>

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String = BaseUtil.KEY_API
    ):NetworkResponse<WeatherResponse, BaseCommonError>
}