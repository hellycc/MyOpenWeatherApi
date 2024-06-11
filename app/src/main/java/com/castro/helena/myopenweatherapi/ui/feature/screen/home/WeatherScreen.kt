package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WeatherScreen(navController: NavController) {
    Text(text = "My Weather Api test")
}

@Preview
@Composable
fun WeatherScreenPreview(modifier: Modifier = Modifier) {
    WeatherScreen(navController = rememberNavController())
}