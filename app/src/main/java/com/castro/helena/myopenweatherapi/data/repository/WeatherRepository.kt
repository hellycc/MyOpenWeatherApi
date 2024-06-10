package com.castro.helena.myopenweatherapi.data.repository

import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherData(lat: Float, lng: Float) : Flow<WeatherInfo>
}