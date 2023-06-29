package com.example.weatherapp.util

fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).toInt()
}

fun Double.kelvinToFahrenheit(): Int {
    return ((this - 273.15) * 9 / 5 + 32).toInt()
}

fun Double?.orZero() = this ?: 0.0
