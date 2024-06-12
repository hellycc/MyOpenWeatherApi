package com.castro.helena.myopenweatherapi.ui.feature.screen.home.event

sealed class WeatherEvent {

    data class FetchWeather(val lat: Float, val lng: Float) : WeatherEvent()
    data class Navigate(val route: String) : WeatherEvent()
}