package com.natife.testtask2.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author admin
 */
@Entity(tableName = "humans")
data class User(
//    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "uuid") val uuid: String?,
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "picture") val picture: String?
)
