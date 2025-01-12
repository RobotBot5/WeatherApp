package com.robotbot.weatherapp.presentation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.robotbot.weatherapp.domain.entity.City
import com.robotbot.weatherapp.presentation.extensions.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultFavouriteComponent @Inject constructor(
    private val storeFactory: FavouriteStoreFactory,
    private val onCityItemClicked: (City) -> Unit,
    private val onAddFavouriteClicked: () -> Unit,
    private val onSearchClicked: () -> Unit,
    componentContext: ComponentContext
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is FavouriteStore.Label.CityItemClicked -> {
                        onCityItemClicked(it.city)
                    }
                    FavouriteStore.Label.ClickSearch -> {
                        onSearchClicked()
                    }
                    FavouriteStore.Label.ClickAddToFavourite -> {
                        onAddFavouriteClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FavouriteStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickAddToFavourite)
    }

    override fun onCityItemClick(city: City) {
        store.accept(FavouriteStore.Intent.CityItemClicked(city))
    }
}