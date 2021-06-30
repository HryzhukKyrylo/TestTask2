package com.natife.testtask2.data.repository

import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User
import com.natife.testtask2.data.local.HumanDao
import javax.inject.Inject

/**
 *@author admin
 */
class MainLocalRepository @Inject constructor(
    private val local: HumanDao
) :MainRepository{
    override suspend fun saveUsers(list: List<User>) {
        local.insertAll(list)
    }

    override suspend fun loadUsers(): List<Human> {
//       return local.getAllHumans()
        throw UnsupportedOperationException("WTF")
    }
}
