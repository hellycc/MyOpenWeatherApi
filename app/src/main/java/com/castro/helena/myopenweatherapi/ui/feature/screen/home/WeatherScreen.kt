package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.event.WeatherEvent

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherInfoState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.dispatch(WeatherEvent.FetchWeather(lat = -3.1190f, lng = -60.0217f))
    weatherInfoState.weatherInfo?.let {

    }
    Text(text = "My Weather Api test")
}

@Preview
@Composable
fun WeatherScreenPreview(modifier: Modifier = Modifier) {
    WeatherScreen(navController = rememberNavController())
}