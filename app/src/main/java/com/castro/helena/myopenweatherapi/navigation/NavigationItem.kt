package com.castro.helena.myopenweatherapi.navigation

import com.castro.helena.myopenweatherapi.navigation.NavRoutes.HOME_SCREEN

sealed class NavigationItem(val route: String) {

    data object HomeScreen : NavigationItem(route = HOME_SCREEN)

}