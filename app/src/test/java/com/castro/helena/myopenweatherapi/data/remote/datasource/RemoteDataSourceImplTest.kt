package com.castro.helena.myopenweatherapi.data.remote.datasource

import com.castro.helena.myopenweatherapi.data.network.ApiConfig
import com.castro.helena.myopenweatherapi.data.network.NetworkResult
import com.castro.helena.myopenweatherapi.data.remote.response.WeatherDataResponse
import com.castro.helena.myopenweatherapi.factory.createClient
import com.castro.helena.myopenweatherapi.factory.ktorErrorClient
import com.castro.helena.myopenweatherapi.factory.readFile
import io.ktor.http.HttpStatusCode
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RemoteDataSourceImplTest {

    private val apiConfig = mockk<ApiConfig>().apply {
        every { apiKey }.answers { "abc" }
        every { baseUrl }.answers { "xyz" }
    }

    private lateinit var remoteDataSource: RemoteDataSource
    private val mockResponseFile = "ktor_client/weather_data_response.json".readFile()

    @Test
    fun `should return success result`() = runBlocking {
        // Arrange // Action // Assertion

        // Given
        remoteDataSource = RemoteDataSourceImpl(
            httpClient = createClient(
                json = mockResponseFile,
                status = HttpStatusCode.OK
            ),
            apiConfig = apiConfig
        )

        // When
        val result = remoteDataSource.getWeatherDataResponse(36.41f, 40.5f)

        // Then
        assertTrue(result is NetworkResult.Success)
        assertEquals(
            Json.decodeFromString<WeatherDataResponse>(mockResponseFile),
            (result as NetworkResult.Success).value
        )
    }

    @Test
    fun `should return error result`() = runBlocking {
        // Given
        remoteDataSource = RemoteDataSourceImpl(
            httpClient = ktorErrorClient,
            apiConfig = apiConfig
        )

        // When
        val result = remoteDataSource.getWeatherDataResponse(36.41f, 40.5f)

        // Then
        assertTrue(result is NetworkResult.Exception)
    }
}