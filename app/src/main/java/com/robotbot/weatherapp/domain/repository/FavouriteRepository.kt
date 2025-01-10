package com.robotbot.weatherapp.domain.repository

import com.robotbot.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteCities: Flow<List<City>>

    fun observerIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)
}