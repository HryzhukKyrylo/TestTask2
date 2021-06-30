package com.natife.testtask2.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.User

/**
 *@author admin
 */
@Dao
interface HumanDao {

//    @Query("SELECT * FROM humans LIMIT 20 OFFSET :offset")
    @Query("SELECT * FROM humans ")
    fun getAllHumans() : List<User>

    @Query("SELECT * FROM humans WHERE uuid = :uuid")
    fun getHuman(uuid: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

}
