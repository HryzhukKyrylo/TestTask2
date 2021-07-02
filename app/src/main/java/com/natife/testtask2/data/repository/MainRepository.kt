package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.entities.UserResponse
import com.natife.testtask2.data.entities.toUser
import com.natife.testtask2.data.local.HumanDao
import com.natife.testtask2.data.remote.RemoteRepository
import com.natife.testtask2.utils.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remote: RemoteRepository,
    private val local: HumanDao
) {
    private var firstRequest = true

    suspend fun loadHumans(): Resource<UserResponse> {
        val result = remote.loadHumans()
        return if (result.status == Resource.Status.SUCCESS) {
            if (firstRequest) {
                firstRequest = false
                local.deleteAll()
            }
            val listUser = arrayListOf<User>()
            result.data?.results?.map {
                listUser.add(it.toUser())
            }

            local.insertAll(listUser)
            Resource.success(result.data!!.apply { resultsUser = local.getAllHumans() })
        } else {
            loadHumansCached()
        }
    }

    fun fetchUser(id: String): User {
        return local.getHuman(id)
    }

    private fun loadHumansCached(): Resource<UserResponse> =
        local.getAllHumans()
            .let {
                Resource.success(UserResponse(resultsUser = it, results = listOf()))
            }
}
