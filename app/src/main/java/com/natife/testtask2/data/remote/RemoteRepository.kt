package com.natife.testtask2.data.remote

import com.natife.testtask2.data.entities.UserResponse
import com.natife.testtask2.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val service: ApiService) {

    suspend fun loadHumans(): Resource<UserResponse> {
        return getResponse(
            request = { service.getHumans() },
            defaultErrorMessage = "Error load Humans"
        )
    }

    private suspend fun <T> getResponse(
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
}
