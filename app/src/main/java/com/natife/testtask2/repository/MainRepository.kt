package com.natife.testtask2.repository

import com.natife.testtask2.api.RetrofitService
import com.natife.testtask2.model.UserResponse
import retrofit2.Call

/**
 *@author admin
 */
interface MainRepository  {
    suspend fun getAllUser():UserResponse
}
