package com.example.weatherapp.data.entity.forecast

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city") val city: City? = null,
    @SerializedName("cnt") val cnt: Int? = null,
    @SerializedName("cod") val cod: String = "",
    @SerializedName("list") val list: List<Detail>? = null,
    @SerializedName("message") val message: Int? = null
)

data class City(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("coord") val coord: Coord? = null,
    @SerializedName("country") val country: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("population") val population: Int? = null,
    @SerializedName("sunrise") val sunrise: Int? = null,
    @SerializedName("sunset") val sunset: Int? = null,
    @SerializedName("timezone") val timezone: Int? = null
)

data class Coord(
    @SerializedName("lat") val lat: Double? = null, @SerializedName("lon") val lon: Double? = null
)

data class Detail(
    @SerializedName("clouds") val clouds: Clouds? = null,
    @SerializedName("dt") val dt: Int? = null,
    @SerializedName("dt_txt") val dt_txt: String? = "",
    @SerializedName("main") val main: Main? = null,
    @SerializedName("pop") val pop: Double? = null,
    @SerializedName("rain") val rain: Rain? = null,
    @SerializedName("sys") val sys: Sys? = null,
    @SerializedName("visibility") val visibility: Int? = null,
    @SerializedName("weather") val weather: List<Weather>? = null,
    @SerializedName("wind") val wind: Wind? = null
)

data class Clouds(
    @SerializedName("all") val all: Int? = null
)

data class Main(
    @SerializedName("feels_like") val feels_like: Double? = null,
    @SerializedName("grnd_level") val grnd_level: Int? = null,
    @SerializedName("humidity") val humidity: Int? = null,
    @SerializedName("pressure") val pressure: Int? = null,
    @SerializedName("sea_level") val sea_level: Int? = null,
    @SerializedName("temp") val temp: Double? = null,
    @SerializedName("temp_kf") val temp_kf: Double? = null,
    @SerializedName("temp_max") val temp_max: Double? = null,
    @SerializedName("temp_min") val temp_min: Double? = null
)

data class Rain(
    @SerializedName("3h") val `3h`: Double? = null
)

data class Sys(
    @SerializedName("pod") val pod: String = ""
)

data class Wind(
    @SerializedName("deg") val deg: Int? = null,
    @SerializedName("gust") val gust: Double? = null,
    @SerializedName("speed") val speed: Double? = null
)

data class Weather(
    @SerializedName("description") val description: String? = "",
    @SerializedName("icon") val icon: String? = "",
    @SerializedName("id") val id: Int? = null,
    @SerializedName("main") val main: String? = ""
)