package com.example.weatherapp.ui.forecast.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemForecastBinding
import com.example.weatherapp.ui.forecast.model.ForecastUi
import com.example.weatherapp.util.loadUrl

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    var forecastList: List<ForecastUi.WeatherUi> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            binding = ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bindView(forecastList.getOrNull(position))

    inner class ForecastViewHolder(
        private val binding: ItemForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: ForecastUi.WeatherUi?) {
            with(binding) {

                tvForecastItemDayNameTitle.text = item?.dayOfWeek
                tvForecastTime.text = item?.time
                tvTempValue.text = item?.tempCelsius.toString()
                ivForecastWeatherIcon.loadUrl(root.context, item?.icon.orEmpty())
            }
        }
    }
}
