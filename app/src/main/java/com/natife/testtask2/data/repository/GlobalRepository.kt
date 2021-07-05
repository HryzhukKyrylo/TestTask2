package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.utils.Resource

interface GlobalRepository {

    suspend fun loadUsers(): Resource<List<User>>

    suspend fun deleteAllUsers()

    suspend fun insertAllUsers(list: List<User>)

    suspend fun findUserById(id: String): User
}
