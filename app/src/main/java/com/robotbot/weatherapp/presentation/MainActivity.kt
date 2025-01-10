package com.robotbot.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.robotbot.weatherapp.data.network.api.ApiFactory
import com.robotbot.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather = apiService.loadCurrentWeather("Moscow")
            val forecast = apiService.loadForecast("Moscow")
            val cities = apiService.searchCity("Moscow")
            Log.d(
                "MainActivity",
                "Current weather: $currentWeather\nForecast weather: $forecast\nCities: $cities"
            )
        }
        setContent {
            WeatherAppTheme {
            }
        }
    }
}