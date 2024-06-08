package com.castro.helena.myopenweatherapi.data.network

import javax.inject.Inject

data class ApiConfig @Inject constructor(
    var baseUrl: String,
    var apiKey: String
)
