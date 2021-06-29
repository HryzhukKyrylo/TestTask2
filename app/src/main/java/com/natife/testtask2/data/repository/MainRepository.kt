package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.UserResponse

/**
 *@author admin
 */
interface MainRepository  {
    suspend fun getAllUser(): UserResponse
}
