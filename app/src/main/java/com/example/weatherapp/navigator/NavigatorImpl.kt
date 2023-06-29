package com.example.weatherapp.navigator

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.ui.forecast.ForecastFragment.Companion.FORECAST_LAT
import com.example.weatherapp.ui.forecast.ForecastFragment.Companion.FORECAST_LON
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class NavigatorImpl @Inject constructor(
    @ActivityContext private val activityContext: Context
) : Navigator {

    private val activity get() = (activityContext as? AppCompatActivity)
    private val fragment get() = activity?.supportFragmentManager?.fragments?.firstOrNull()
    private val defaultOptionsBuilder by lazy { NavOptions.Builder() }

    override val navController get() = fragment?.findNavController()

    override fun navigateToForecast(lat:Double, lon:Double) {
        navController.fade(R.id.navigate_to_forecast, Bundle().apply {
            putDouble(FORECAST_LAT,lat)
            putDouble(FORECAST_LON,lon)
        })
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    private fun NavController?.fade(id: Int, bundle: Bundle? = null) =
        this?.navigate(id, bundle)

}