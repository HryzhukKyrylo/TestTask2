package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.utils.Resource
import javax.inject.Inject

class MainRepositoryDecorator @Inject constructor(
    private val remote: GlobalRepository,
    private val local: GlobalRepository
) : GlobalRepository {
    private var firstRequest = true

    override suspend fun loadUsers(): Resource<List<User>> {
        val result = remote.loadUsers()
        return if (result.status == Resource.Status.SUCCESS) {
            if (firstRequest) {
                firstRequest = false
                deleteAllUsers()
            }
            insertAllUsers(result.data ?: listOf())
            local.loadUsers()
        } else {
            local.loadUsers()
        }
    }

    override suspend fun deleteAllUsers() {
        local.deleteAllUsers()
    }

    override suspend fun insertAllUsers(list: List<User>) {
        local.insertAllUsers(list)
    }

    override suspend fun findUserById(id: String): User {
        return local.findUserById(id)
    }
}
