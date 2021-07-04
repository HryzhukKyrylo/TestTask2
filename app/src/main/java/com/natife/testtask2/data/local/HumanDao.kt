package com.natife.testtask2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natife.testtask2.data.entities.User

@Dao
interface HumanDao {

    @Query("SELECT * FROM humans ")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM humans WHERE uuid = :uuid")
    fun getUser(uuid: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM humans")
    suspend fun deleteAllUsers()

}