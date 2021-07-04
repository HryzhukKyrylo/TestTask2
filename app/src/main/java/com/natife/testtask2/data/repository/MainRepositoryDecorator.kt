package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.utils.Resource
import javax.inject.Inject

class MainRepositoryDecorator @Inject constructor(
    private val remote: RemoteRepository,
    private val local: LocalRepository
) {
    private var firstRequest = true

    suspend fun loadHumans(): Resource<List<User>> {
        val result = remote.loadUsers()
        return if (result.status == Resource.Status.SUCCESS) {
            if (firstRequest) {
                firstRequest = false
                local.deleteAllUsers()
            }
            local.insertAllUsers(result.data ?: listOf())
            local.loadUsers()
        } else {
            local.loadUsers()
        }
    }

    fun fetchUser(id: String): User {
        return local.getUserFromId(id)
    }
}