package com.castro.helena.myopenweatherapi.data.remote

import com.castro.helena.myopenweatherapi.data.network.NetworkResult
import com.castro.helena.myopenweatherapi.data.remote.response.WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat: Float, lng: Float) : NetworkResult<WeatherDataResponse>
}