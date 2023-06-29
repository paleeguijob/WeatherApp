package com.example.weatherapp.navigator

import androidx.navigation.NavController

interface Navigator {
    val navController: NavController?

    fun navigateToForecast(lat:Double, lon:Double)
    fun navigateBack()
}