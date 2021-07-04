package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.local.HumanDao
import com.natife.testtask2.utils.Resource
import com.natife.testtask2.utils.getResponse
import javax.inject.Inject

class LocalRepository @Inject constructor(private val local: HumanDao) : GlobalRepository {
    suspend fun deleteAllUsers() = local.deleteAllUsers()

    suspend fun insertAllUsers(list: List<User>) = local.insertAllUsers(list)

    fun getUserFromId(id: String) = local.getUser(id)

    override suspend fun loadUsers(): Resource<List<User>> {
        return getResponse(
            request = {
                local.getAllUsers()
            },
            defaultErrorMessage = "Error load Humans"
        )
    }
}