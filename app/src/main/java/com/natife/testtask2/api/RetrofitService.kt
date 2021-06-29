package com.natife.testtask2.api

import com.natife.testtask2.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("api/")
    suspend fun getUsers(
        @Query("results")results:Int = 10
    ):UserResponse
//    @POST()
//    fun updateSomeData(@RequestBody newData: Data): Call<UserResponse>

}

