package com.natife.testtask2.utils

 suspend fun <T> getResponse(
    request: suspend () -> T,
    defaultErrorMessage: String
): Resource<T> {
    return try {
        val result = request.invoke()
        return Resource.success(result)
    } catch (e: Throwable) {
        e.printStackTrace()
        Resource.error(data = null, message = defaultErrorMessage)
    }
}