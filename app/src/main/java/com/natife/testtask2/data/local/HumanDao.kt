package com.natife.testtask2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natife.testtask2.data.entities.User

/**
 *@author admin
 */
@Dao
interface HumanDao {

    @Query("SELECT * FROM humans ")
    fun getAllHumans() : List<User>

    @Query("SELECT * FROM humans WHERE uuid = :uuid")
    fun getHuman(uuid: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM humans")
    suspend fun deleteAll()

}
