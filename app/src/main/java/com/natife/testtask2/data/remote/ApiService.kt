package com.natife.testtask2.data.remote

import com.natife.testtask2.data.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getHumans(
        @Query("results")results:Int = 10
    ): UserResponse
}
