package com.natife.testtask2.repository

import com.natife.testtask2.model.User
import com.natife.testtask2.model.UserResponse
import retrofit2.Call

/**
 *@author admin
 */
interface UserRepository {

    suspend fun getUserRemote(
        lat: Double,
        lon: Double,
        units: String
    ): Call<UserResponse>

    suspend fun getUserLocale(): List<User>

}
