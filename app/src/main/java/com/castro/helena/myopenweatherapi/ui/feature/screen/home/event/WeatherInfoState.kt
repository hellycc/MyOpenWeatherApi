package com.castro.helena.myopenweatherapi.ui.feature.screen.home.event

import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo

data class WeatherInfoState(
    val weatherInfo: WeatherInfo? = null,
    val destination: String? = null,
    val loading: Boolean = false
)
