package com.castro.helena.myopenweatherapi.domain.usecase

import com.castro.helena.myopenweatherapi.data.network.CustomException
import com.castro.helena.myopenweatherapi.data.remote.response.WeatherDataResponse
import com.castro.helena.myopenweatherapi.data.repository.WeatherRepository
import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import com.castro.helena.myopenweatherapi.factory.readFile
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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

class GetWeatherUseCaseImplTest {

    private val repository = mockk<WeatherRepository>()
    private val mockResponseFile = "ktor_client/weather_data_response.json".readFile()
    private val mockDomain =
        Json.decodeFromString<WeatherDataResponse>(mockResponseFile).toMockDomain()
    private lateinit var useCase: GetWeatherUseCase

    @Before
    fun setup() {
        useCase = GetWeatherUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should return weather info according to given latitude and longitude`() = runBlocking {
        // Given
        coEvery { repository.getWeatherData(any(), any()) } returns flowOf(mockDomain)

        // When
        val result = useCase.getWeatherInfo(0f, 0f).first()

        // Then
        assertEquals(mockDomain, result)
    }

    @Test
    fun `should return http error 404`() = runBlocking {
        // Given
        coEvery {
            repository.getWeatherData(
                any(),
                any()
            )
        } returns flow { throw CustomException("Http error 404") }

        // When
        val result = useCase.getWeatherInfo(0f, 0f)

        // Then
        result.catch {
            assertEquals("Http error 404", it.message)
        }.collect {}
    }

    private fun WeatherDataResponse.toMockDomain(): WeatherInfo =
        WeatherInfo(
            locationName = this.name,
            conditionIcon = this.weather[0].icon,
            condition = this.weather[0].description,
            temperature = this.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            ),
            isDay = isLightTheme()
        )

    private fun isLightTheme(): Boolean {
        val time = LocalTime.now()
        val startNight = LocalTime.of(18, 0)
        val endNight = LocalTime.of(6, 0)
        return !(time in startNight..LocalTime.MAX || time in LocalTime.MIN..endNight)
    }
}
