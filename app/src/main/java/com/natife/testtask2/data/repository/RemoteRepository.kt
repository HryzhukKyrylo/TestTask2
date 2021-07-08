package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.remote.ApiService
import com.natife.testtask2.utils.Const
import com.natife.testtask2.utils.Resource
import com.natife.testtask2.utils.getResponse
import com.natife.testtask2.utils.toUser

class RemoteRepository (private val service: ApiService) : GlobalRepository {

    override suspend fun loadUsers(): Resource<List<User>> {
        return getResponse(
            request = {
                val listUser = arrayListOf<User>()
                service.getUsers().results.forEach {
                    listUser.add(it.toUser())
                }
                listUser
            },
            defaultErrorMessage = Const.ERROR_LOAD_REMOTE
        )
    }

    override suspend fun deleteAllUsers() {
        throw IllegalArgumentException(Const.ERROR_DELETE)
    }

    override suspend fun insertAllUsers(list: List<User>) {
        throw IllegalArgumentException(Const.ERROR_INSERT)
    }

    override suspend fun findUserById(id: String): User {
        throw IllegalArgumentException(Const.ERROR_FIND)
    }
}
