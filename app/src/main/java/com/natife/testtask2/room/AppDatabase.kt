package com.natife.testtask2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natife.testtask2.model.User

/**
 *@author admin
 */
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}
