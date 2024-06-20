package com.castro.helena.myopenweatherapi.data.remote.datasource

import com.castro.helena.myopenweatherapi.data.network.ApiConfig
import com.castro.helena.myopenweatherapi.factory.readFile
import io.mockk.every
import io.mockk.mockk

class RemoteDataSourceImplTest {

    private val apiConfig = mockk<ApiConfig>().apply {
        every { apiKey }.answers { "abc" }
        every { baseUrl }.answers { "xyz" }
    }

    private lateinit var remoteDataSource: RemoteDataSource

    private val mockResponseFile = "ktor_client/weather_data_response.json".readFile()
}