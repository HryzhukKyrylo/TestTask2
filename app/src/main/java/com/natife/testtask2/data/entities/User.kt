package com.natife.testtask2.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "humans")
data class User(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "uuid") val uuid: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "picture") val picture: String?
)
