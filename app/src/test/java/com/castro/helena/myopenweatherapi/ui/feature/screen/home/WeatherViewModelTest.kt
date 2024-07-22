package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import com.castro.helena.myopenweatherapi.data.network.CustomException
import com.castro.helena.myopenweatherapi.domain.model.WeatherInfo
import com.castro.helena.myopenweatherapi.domain.usecase.GetWeatherUseCase
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherEvent
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private val useCase = mockk<GetWeatherUseCase>()
    private lateinit var viewModel: WeatherViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = WeatherViewModel(useCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return weather info of given lat and lng`() = runBlocking {
        // Given
        coEvery { useCase.getWeatherInfo(any(), any()) } returns flowOf(mockWeatherInfo())

        // When
        viewModel.dispatch(WeatherEvent.FetchWeather(0f, 0f))

        // Then
        assertEquals(viewModel.uiState.value.weatherInfo, mockWeatherInfo())
    }

    @Test
    fun `should return http error 404 when wrong lat and lng passed`() = runBlocking {
        // Given
        coEvery {
            useCase.getWeatherInfo(
                any(),
                any()
            )
        } returns flow { throw CustomException() }

        // When
        val result = viewModel.dispatch(WeatherEvent.Unknown)

        // Then
        assertEquals(Unit, result)
    }

    private fun mockWeatherInfo() = WeatherInfo(
        locationName = "Mountain View",
        conditionIcon = "01d",
        condition = "céu limpo",
        temperature = 17.33.toInt(),
        dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
        isDay = isLightTheme()
    )

    private fun isLightTheme(): Boolean {
        val time = LocalTime.now()
        val startNight = LocalTime.of(18, 0)
        val endNight = LocalTime.of(6, 0)
        return !(time in startNight..LocalTime.MAX || time in LocalTime.MIN..endNight)
    }
}