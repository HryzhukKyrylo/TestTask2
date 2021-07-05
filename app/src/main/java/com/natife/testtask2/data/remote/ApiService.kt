package com.natife.testtask2.data.remote

import com.natife.testtask2.data.entities.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(POINT)
    suspend fun getUsers(
        @Query("results") results: Int = SIZE_REQUEST
    ): UserResponse

    companion object {
        const val POINT = "api/"
        const val SIZE_REQUEST = 10
    }
}
