package com.example.weatherapp.ui.landing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.base.model.BaseCommonError
import com.example.weatherapp.databinding.LandingWeatherFragmentBinding
import com.example.weatherapp.navigator.Navigator
import com.example.weatherapp.ui.landing.LandingWeatherFragmentViewModel.Companion.CITY_DEFAULT
import com.example.weatherapp.ui.landing.model.LandingWeatherUi
import com.example.weatherapp.util.kelvinToCelsius
import com.example.weatherapp.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingWeatherFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: LandingWeatherFragmentBinding

    private val viewModel: LandingWeatherFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LandingWeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            srlRefreshFirstFragment.setOnRefreshListener {
                viewModel.fetchGeoThenFetchWeather(CITY_DEFAULT)
                srlRefreshFirstFragment.isRefreshing = false
            }

            etFindCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard()
                    viewModel.fetchGeoThenFetchWeather(etFindCity.text.toString())
                    etFindCity.text?.clear()

                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            viewModel.fetchGeoThenFetchWeather(CITY_DEFAULT)

            landingWeatherUi.observe(viewLifecycleOwner, ::observeLandingWeatherUi)
        }
    }

    private fun observeLandingWeatherUi(uiModel: LandingWeatherUi) {
        when (uiModel.uiState) {
            is LandingWeatherUi.UiState.Loading -> showLoading()
            is LandingWeatherUi.UiState.Success -> onSuccess(uiModel.uiState.data)
            is LandingWeatherUi.UiState.Error -> showErrorDialog(uiModel.uiState.error)
        }
    }

    private fun onSuccess(data: LandingWeatherUi.WeatherUi) {
        hideLoading()

        with(binding) {
            tvTempValue.text = data.temp.kelvinToCelsius().toString()
            tvCityTitle.text = data.cityName
            tvHumidityValue.text = data.humidity.toString()
            ivWeatherIcon.loadUrl(
                requireContext(),
                data.icon
            )

            setupSwitchTempUnitListener(data)
            setNavigateToForecastListener(data.lat, data.lon)
        }
    }

    private fun setupSwitchTempUnitListener(data: LandingWeatherUi.WeatherUi) {
        with(binding) {
            switchTempUnit.setOnClickListener {
                data.isCelsius = data.isCelsius?.not()
                tvTempValue.text = viewModel.switchTemp(data.isCelsius ?: true, data.temp)
                tvTemperatureUnit.text =
                    if (data.isCelsius != false) getText(R.string.landing_weather_celsius_title) else getText(
                        R.string.landing_weather_fahrenheit_title
                    )
            }
        }
    }

    private fun setNavigateToForecastListener(lat: Double, lon: Double) {
        binding.ivNavigateToForecast.setOnClickListener {
            navigator.navigateToForecast(lat, lon)
        }
    }

    private fun showLoading() {
        with(binding) {
            vwCommonLoading.root.visibility = View.VISIBLE
            landingWeatherContent.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        with(binding) {
            vwCommonLoading.root.visibility = View.GONE
            landingWeatherContent.visibility = View.VISIBLE
        }
    }

    private fun hideKeyboard(){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            val view = requireActivity().currentFocus ?: return
            it.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showErrorDialog(error: BaseCommonError) {
        val message = when (error) {
            is BaseCommonError.ApiError -> error.message
            is BaseCommonError.NetworkError -> error.exception.message
            is BaseCommonError.OtherError -> error.message
        }

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.common_error_title))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.common_error_ok)) { dialog, _ ->
                viewModel.fetchGeoThenFetchWeather(CITY_DEFAULT)
                dialog.dismiss()
            }
            .show()
    }
}