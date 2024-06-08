package com.castro.helena.myopenweatherapi.data

import com.castro.helena.myopenweatherapi.data.network.ApiConfig
import com.castro.helena.myopenweatherapi.data.network.NetworkResult
import com.castro.helena.myopenweatherapi.data.network.safeApiCall
import com.castro.helena.myopenweatherapi.data.remote.RemoteDataSource
import com.castro.helena.myopenweatherapi.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val apiConfig: ApiConfig
) : RemoteDataSource {

    override suspend fun getWeatherDataResponse(
        lat: Float,
        lng: Float
    ): NetworkResult<WeatherDataResponse> =
        safeApiCall {
            httpClient.get(urlString = "${apiConfig.baseUrl}/weather?lat=$lat&lon=$lng&lang=pt_br&appid=${apiConfig.apiKey}&units=metric")
                .body<WeatherDataResponse>()
        }
}