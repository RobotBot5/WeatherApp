package com.robotbot.weatherapp.domain.repository

import com.robotbot.weatherapp.domain.entity.Forecast
import com.robotbot.weatherapp.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast
}