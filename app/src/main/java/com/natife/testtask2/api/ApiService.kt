package com.natife.testtask2.api

import com.natife.testtask2.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET()
    fun getSomeData(): Call<UserResponse>
//    @POST()
//    fun updateSomeData(@RequestBody newData: Data): Call<UserResponse>
}

