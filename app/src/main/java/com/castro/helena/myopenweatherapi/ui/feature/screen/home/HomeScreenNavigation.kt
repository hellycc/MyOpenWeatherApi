package com.castro.helena.myopenweatherapi.ui.feature.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.castro.helena.myopenweatherapi.navigation.NavigationItem

fun NavGraphBuilder.homeScreenNavigation(navController: NavController) {
    composable(route = NavigationItem.HomeScreen.route) {
        WeatherScreen(navController = navController)
    }
}