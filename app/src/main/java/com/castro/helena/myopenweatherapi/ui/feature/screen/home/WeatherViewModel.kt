package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import com.castro.helena.myopenweatherapi.core.BaseViewModel
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherEvent
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : BaseViewModel<WeatherEvent, Unit, WeatherInfoState>() {
    override val initialState: WeatherInfoState
        get() = WeatherInfoState()

    override fun dispatch(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.FetchWeather -> {}
            is WeatherEvent.Navigate -> {}
        }
    }

}