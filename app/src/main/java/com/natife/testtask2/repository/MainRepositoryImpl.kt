package com.natife.testtask2.repository

import com.natife.testtask2.api.RetrofitService
import com.natife.testtask2.model.UserResponse
import retrofit2.Call
import javax.inject.Inject

/**
 *@author admin
 */
class MainRepositoryImpl @Inject constructor(private val service: RetrofitService): MainRepository{
    override suspend fun getAllUser(): UserResponse = service.getUsers()
}
