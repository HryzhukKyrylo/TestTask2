package com.natife.testtask2.repository

import com.natife.testtask2.model.User
import com.natife.testtask2.model.UserResponse
import com.natife.testtask2.room.UserDao
import retrofit2.Call

/**
 *@author admin
 */
class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun getUserRemote(
        lat: Double,
        lon: Double,
        units: String
    ): Call<UserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserLocale(): List<User> {
        return userDao.getAll()
    }
}
