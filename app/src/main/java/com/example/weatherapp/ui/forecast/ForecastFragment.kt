package com.example.weatherapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.databinding.ForecastFragmentBinding
import com.example.weatherapp.navigator.Navigator
import com.example.weatherapp.ui.forecast.adpter.ForecastAdapter
import com.example.weatherapp.ui.forecast.model.ForecastUi
import com.example.weatherapp.util.orZero
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: ForecastFragmentBinding

    private val viewModel: ForecastFragmentViewModel by viewModels()

    private var forecastAdapter: ForecastAdapter? = null

    private val lat by lazy {
        arguments?.getDouble(FORECAST_LAT)
    }
    private val lon by lazy {
        arguments?.getDouble(FORECAST_LON)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ForecastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListener()
        setupViewModel()
    }

    private fun setupView() {
        with(binding.rvForecastWeather) {
            forecastAdapter = ForecastAdapter()
            adapter = forecastAdapter
        }
    }

    private fun setupListener(){
        with(binding){
            ivForecastNavigateBack.setOnClickListener {
                navigator.navigateBack()
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            fetchForecastData(lat.orZero(), lon.orZero())

            forecastUi.observe(viewLifecycleOwner, ::observeForecastData)
        }
    }

    private fun observeForecastData(uiModel: ForecastUi) {
        when (uiModel.uiState) {
            is ForecastUi.UiState.Loading -> showLoading()
            is ForecastUi.UiState.Success -> onForecastSuccess(uiModel.uiState.data)
            is ForecastUi.UiState.Error -> {}
        }
    }

    private fun onForecastSuccess(data: List<ForecastUi.WeatherUi>) {
        hideLoading()
        forecastAdapter?.forecastList = data
    }

    private fun showLoading() {
        with(binding) {
            vwCommonLoading.root.visibility = View.VISIBLE
            ivForecastNavigateBack.visibility = View.GONE
            rvForecastWeather.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        with(binding) {
            vwCommonLoading.root.visibility = View.GONE
            ivForecastNavigateBack.visibility = View.VISIBLE
            rvForecastWeather.visibility = View.VISIBLE
        }
    }

    companion object {
        const val FORECAST_LAT = "FORECAST_LAT"
        const val FORECAST_LON = "FORECAST_LON"
    }
}