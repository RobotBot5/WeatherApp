package com.robotbot.weatherapp.data.repository

import com.robotbot.weatherapp.data.mapper.toEntities
import com.robotbot.weatherapp.data.network.api.ApiService
import com.robotbot.weatherapp.domain.entity.City
import com.robotbot.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> =
        apiService.searchCity(query).toEntities()
}