package com.castro.helena.myopenweatherapi.domain.usecase

import com.castro.helena.myopenweatherapi.data.repository.WeatherRepository
import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetWeatherUseCase {
    suspend fun getWeatherInfo(lat: Float, lng: Float) : Flow<WeatherInfo>
}

class GetWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : GetWeatherUseCase {
    override suspend fun getWeatherInfo(lat: Float, lng: Float): Flow<WeatherInfo> =
        weatherRepository.getWeatherData(lat, lng)
}