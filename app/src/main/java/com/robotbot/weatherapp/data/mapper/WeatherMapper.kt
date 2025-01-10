package com.robotbot.weatherapp.data.mapper

import com.robotbot.weatherapp.data.network.dto.DayDto
import java.util.Calendar
import com.robotbot.weatherapp.data.network.dto.WeatherCurrentDto
import com.robotbot.weatherapp.data.network.dto.WeatherDto
import com.robotbot.weatherapp.data.network.dto.WeatherForecastDto
import com.robotbot.weatherapp.domain.entity.Forecast
import com.robotbot.weatherapp.domain.entity.Weather
import java.util.Date

fun WeatherCurrentDto.toEntity(): Weather = current.toEntity()

fun WeatherForecastDto.toEntity() = Forecast(
    currentWeather = current.toEntity(),
    upcoming = forecastDto.forecastDay.dropCurrentDay().map { dayDto ->
        val dayWeatherDto = dayDto.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionUrl = dayWeatherDto.conditionDto.iconUrl.correctImageUrl(),
            date = dayDto.date.toCalendar()
        )
    }
)

private fun List<DayDto>.dropCurrentDay() = drop(1)

fun WeatherDto.toEntity(): Weather = Weather(
    tempC = tempC,
    conditionText = conditionDto.text,
    conditionUrl = conditionDto.iconUrl.correctImageUrl(),
    date = date.toCalendar()
)

private fun String.correctImageUrl() = "https:$this".replace(
    oldValue = "64x64",
    newValue = "128x128"
)

private const val MILLIS_IN_SECOND = 1000

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar * MILLIS_IN_SECOND)
}