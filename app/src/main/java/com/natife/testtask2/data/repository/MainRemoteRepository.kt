package com.natife.testtask2.data.repository

import android.util.Log
import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.entities.toUser
import com.natife.testtask2.data.local.HumanDao
import com.natife.testtask2.data.remote.ApiService
import javax.inject.Inject

class MainRemoteRepository @Inject constructor(
    private val service: ApiService
) : MainRepository {

//    private var firstRequest = true
//    suspend fun getUsers(): List<User> {
//        return try {
//            val users = service.getHumans()
////            if (firstRequest) {
////                firstRequest = false
////                local.clearUsers()
////            }
////            local.insertAll(users.resultsUser)
//            users.resultsUser
//        } catch (e: Exception) {
////            local.getAllHumans()
//
//        }
//    }

    override suspend fun saveUsers(list: List<User>) {
        throw UnsupportedOperationException("cannot save users to remote")
    }

    override suspend fun loadUsers(): List<Human> {
        return try {
//            val list = arrayListOf<User>()
//            service.getHumans().results.map { element ->
//                list.add(element.toUser())
//            }
//            service.getHumans().resultsUser.apply { this.addAll(list) }
            service.getHumans().results
        } catch (e: Exception) {
            listOf<Human>()
        }
    }
}
