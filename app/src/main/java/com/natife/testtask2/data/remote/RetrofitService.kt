package com.natife.testtask2.data.remote

import com.natife.testtask2.data.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("api/")
    suspend fun getUsers(
        @Query("results")results:Int = 10
    ): UserResponse
//    @POST()
//    fun updateSomeData(@RequestBody newData: Data): Call<UserResponse>

}

