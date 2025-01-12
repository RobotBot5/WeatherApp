package com.robotbot.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.robotbot.weatherapp.presentation.details.DetailsContent
import com.robotbot.weatherapp.presentation.favourite.FavouriteContent
import com.robotbot.weatherapp.presentation.search.SearchContent
import com.robotbot.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {

    WeatherAppTheme {
        Children(
            modifier = modifier,
            stack = component.stack
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(instance.component)
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(instance.component)
                }
                is RootComponent.Child.Search -> {
                    SearchContent(instance.component)
                }
            }
        }
    }
}