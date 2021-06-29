package com.natife.testtask2.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User

/**
 *@author admin
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM humans") fun getAll(): List<User>
    @Insert
    fun insertAll(vararg users: User)
    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM humans")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM humans WHERE first_name = :name")
    fun getUser(name: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

}
