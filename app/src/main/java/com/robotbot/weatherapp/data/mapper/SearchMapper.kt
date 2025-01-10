package com.robotbot.weatherapp.data.mapper

import com.robotbot.weatherapp.data.network.dto.CityDto
import com.robotbot.weatherapp.domain.entity.City

fun CityDto.toEntity(): City = City(id, name, country)

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }