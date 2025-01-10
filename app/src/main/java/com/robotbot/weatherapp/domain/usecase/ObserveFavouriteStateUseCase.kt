package com.robotbot.weatherapp.domain.usecase

import com.robotbot.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    operator fun invoke(cityId: Int) = repository.observerIsFavourite(cityId)
}