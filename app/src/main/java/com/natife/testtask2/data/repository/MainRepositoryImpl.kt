package com.natife.testtask2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.load.engine.Resource
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.remote.RetrofitService
import com.natife.testtask2.data.entities.UserResponse
import com.natife.testtask2.data.local.AppDatabase
import com.natife.testtask2.data.local.UserDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 *@author admin
 */
class MainRepositoryImpl @Inject constructor(
    private val database: UserDao,
    private val service: RetrofitService): MainRepository {
    override suspend fun getAllUser(): UserResponse = service.getUsers()
    suspend fun setUserDatabase(user: User) = database.insert(user)

}
