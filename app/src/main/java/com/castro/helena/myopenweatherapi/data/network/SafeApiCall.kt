package com.castro.helena.myopenweatherapi.data.network

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> =
    try {
        NetworkResult.Success(value = apiCall.invoke())
    } catch (exception: Exception) {
        NetworkResult.Exception(exception)
    } catch (customException: CustomException) {
        mapCustomException(customException)
    }

class CustomException(
    message: String? = null,
    val apiError: ApiError? = null
) : Exception(message)

fun <T> mapCustomException(customException: CustomException): NetworkResult<T> =
    NetworkResult.Error(
        code = customException.apiError?.code ?: "0",
        errorMessage = customException.apiError?.message ?: "Unknown error"
    )

