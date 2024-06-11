package com.castro.helena.myopenweatherapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.castro.helena.myopenweatherapi.ui.feature.screen.home.homeScreenNavigation

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        homeScreenNavigation(navController = navHostController)
    }
}