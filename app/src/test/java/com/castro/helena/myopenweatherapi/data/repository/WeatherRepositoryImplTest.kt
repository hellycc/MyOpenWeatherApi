package com.castro.helena.myopenweatherapi.data.repository

import com.castro.helena.myopenweatherapi.data.network.NetworkResult
import com.castro.helena.myopenweatherapi.data.remote.datasource.RemoteDataSource
import com.castro.helena.myopenweatherapi.data.remote.response.WeatherDataResponse
import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import com.castro.helena.myopenweatherapi.factory.readFile
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

class WeatherRepositoryImplTest {

    private val mockRemoteDataSource = mockk<RemoteDataSource>()
    private val mockResponseFile = "ktor_client/weather_data_response.json".readFile()
    private val response = Json.decodeFromString<WeatherDataResponse>(mockResponseFile)
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = WeatherRepositoryImpl(mockRemoteDataSource)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when get weather data called returns weather info`() = runBlocking {
        // Given
        coEvery { mockRemoteDataSource.getWeatherDataResponse(any(), any()) } returns NetworkResult.Success(response)

        // When
        val result = repository.getWeatherData(0f, 0f)

        // Then
        result.collect {
            assertEquals(it, response.toMockDomain())
        }
    }

    @Test
    fun `when get weather data called returns error`() = runBlocking {
        // Given
        coEvery { mockRemoteDataSource.getWeatherDataResponse(any(), any()) } returns
                NetworkResult.Error("404", "Not found")

        // When
        val result = repository.getWeatherData(0f, 0f)

        // Then
        result.catch {
            assertEquals("Not found", it.message)
        }.collect {}
    }

    private fun WeatherDataResponse.toMockDomain(): WeatherInfo =
        WeatherInfo(
            locationName = this.name,
            conditionIcon = this.weather[0].icon,
            condition = this.weather[0].description,
            temperature = this.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = isDayOrNight()
        )

    private fun isDayOrNight(): Boolean {
        val time = LocalTime.now()
        val startNight = LocalTime.of(18, 0)
        val endNight = LocalTime.of(6, 0)

        return !(time in startNight..LocalTime.MAX || time in LocalTime.MIN..endNight)
    }

}