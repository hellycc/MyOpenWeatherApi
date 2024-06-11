package com.castro.helena.myopenweatherapi.ui.feature.screen.home.event

sealed class WeatherEvent {

    data object FetchWeather : WeatherEvent()
    data class Navigate(val route: String) : WeatherEvent()
}