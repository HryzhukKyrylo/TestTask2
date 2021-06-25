package com.natife.testtask2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.natife.testtask2.model.User

/**
 *@author admin
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user") fun getAll(): List<User>
    @Insert
    fun insertAll(vararg users: User)
    @Delete
    fun delete(user: User)
}
