package com.robotbot.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.defaultComponentContext
import com.robotbot.weatherapp.WeatherApp
import com.robotbot.weatherapp.data.network.api.ApiFactory
import com.robotbot.weatherapp.domain.usecase.ChangeFavouriteStateUseCase
import com.robotbot.weatherapp.domain.usecase.SearchCityUseCase
import com.robotbot.weatherapp.presentation.root.DefaultRootComponent
import com.robotbot.weatherapp.presentation.root.RootContent
import com.robotbot.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeatherApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = rootComponentFactory.create(defaultComponentContext())

        setContent {
            RootContent(
                modifier = Modifier.padding(top = 30.dp),
                component = root
            )
        }
    }
}