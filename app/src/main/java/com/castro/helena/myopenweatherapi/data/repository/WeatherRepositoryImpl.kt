package com.castro.helena.myopenweatherapi.data.repository

import com.castro.helena.myopenweatherapi.data.network.toFlow
import com.castro.helena.myopenweatherapi.data.remote.datasource.RemoteDataSource
import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import com.castro.helena.myopenweatherapi.domain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Float, lng: Float): Flow<WeatherInfo> =
        remoteDataSource.getWeatherDataResponse(lat, lng).toFlow().map {
            it.toDomain()
        }
}