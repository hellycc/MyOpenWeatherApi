package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import androidx.lifecycle.viewModelScope
import com.castro.helena.myopenweatherapi.core.BaseViewModel
import com.castro.helena.myopenweatherapi.domain.usecase.GetWeatherUseCase
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherEvent
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCase: GetWeatherUseCase) :
    BaseViewModel<WeatherEvent, Unit, WeatherInfoState>() {
    override val initialState: WeatherInfoState
        get() = WeatherInfoState()

    override fun dispatch(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.FetchWeather -> {
                getWeatherInfo(event.lat, event.lng)
            }

            is WeatherEvent.Navigate -> {}
            else -> {}
        }
    }

    private fun getWeatherInfo(lat: Float, lng: Float) = viewModelScope.launch {
        useCase.getWeatherInfo(lat, lng)
            .onStart { loading(true) }
            .onCompletion { loading(false) }
            .flowOn(Dispatchers.IO)
            .catch {
                println("Exception")
            }
            .collect { weatherInfo ->
                updateUiState(uiState = uiState.value.copy(weatherInfo = weatherInfo))
            }
    }

    private fun loading(isLoading: Boolean) {
        updateUiState(uiState = uiState.value.copy(loading = isLoading))
    }
}