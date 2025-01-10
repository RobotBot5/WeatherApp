package com.robotbot.weatherapp.domain.repository

import com.robotbot.weatherapp.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>
}