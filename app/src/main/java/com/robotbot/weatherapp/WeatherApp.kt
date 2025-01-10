package com.robotbot.weatherapp

import android.app.Application
import com.robotbot.weatherapp.di.ApplicationComponent
import com.robotbot.weatherapp.di.DaggerApplicationComponent

class WeatherApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}