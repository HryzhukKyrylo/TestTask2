package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User
import java.lang.Exception

/**
 *@author admin
 */
interface MainRepository {
    suspend fun saveUsers(list: List<User>)
    suspend fun loadUsers(): List<Human>
}

//class DatabaseRepo : MainRepository {
//
//    override suspend fun saveUsers() {
//
//    }
//
//    override suspend fun loadUsers(): List<User> {
//        return listOf()
//    }
//}
//
//class NetworkRepo : MainRepository {
//
//    override suspend fun saveUsers() {
//        throw UnsupportedOperationException("cannot save users to remote")
//    }
//
//    override suspend fun loadUsers(): List<User> {
//        return listOf()
//    }
//
//}
//
//class UserRepoImpl(
//    private val databaseRepo: MainRepository,
//    private val networkRepo: MainRepository
//) : MainRepository {
//
//    private var firstLoad = true
//    override suspend fun saveUsers() {
//        databaseRepo.saveUsers()
//    }
//
//    override suspend fun loadUsers(): List<User> {
//        return try {
//            val users = networkRepo.loadUsers()
//            if (firstLoad) {
//                firstLoad = false
//                databaseRepo.clear()
//            }
//            databaseRepo.saveUsers()
//            users
//
//        } catch (e: Exception) {
//            databaseRepo.loadUsers()
//        }
//    }
//
//}
