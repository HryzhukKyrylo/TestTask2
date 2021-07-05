package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.remote.ApiService
import com.natife.testtask2.utils.Resource
import com.natife.testtask2.utils.getResponse
import com.natife.testtask2.utils.toUser
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val service: ApiService) : GlobalRepository {
    override suspend fun loadUsers(): Resource<List<User>> {
        return getResponse(
            request = {
                val listUser = arrayListOf<User>()
                service.getUsers().results.forEach {
                    listUser.add(it.toUser())
                }
                listUser
            },
            defaultErrorMessage = "Error load Humans"
        )
    }

    override suspend fun deleteAllUsers() {
        throw IllegalArgumentException("ApiService not realisation deleteAllUsers")
    }

    override suspend fun insertAllUsers(list: List<User>) {
        throw IllegalArgumentException("ApiService not realisation insertAllUsers")
    }

    override suspend fun findUserById(id: String): User {
        throw IllegalArgumentException("ApiService not realisation findUserById")
    }
}
