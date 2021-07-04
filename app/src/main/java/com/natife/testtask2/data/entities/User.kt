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

class UserBuilder() {
    private var firstName = ""
    private var lastName = ""
    private var title = ""
    private var age = 0
    private var phone = ""
    private var email = ""
    private var uuid = ""
    private var id: Int = 0
    private var picture = ""

    fun withFirstName(value: String) = apply {
        this.firstName = value
    }

    fun withLastName(value: String) = apply {
        this.lastName = value
    }

    fun withTittle(value: String) = apply {
        this.title = value
    }

    fun withAge(value: Int) = apply {
        this.age = value
    }

    fun withPhone(value: String) = apply {
        this.phone = value
    }

    fun withEmail(value: String) = apply {
        this.email = value
    }

    fun withUuid(value: String) = apply {
        this.uuid = value
    }

    fun withId(value: String) = apply {
        this.firstName = value
    }

    fun withPicture(value: String) = apply {
        this.picture = value
    }

    fun build() = User(
        firstName = firstName,
        lastName = lastName,
        title = title,
        age = age,
        uuid = uuid,
        picture = picture,
        phone = phone,
        email = email
    )


//    fun withFirstName(value: String): UserBuilder {
//        this.firstName = value
//        return this
//    }
//
//
//    fun withLastName(value: String): UserBuilder {
//        this.lastName = value
//        return this
//    }
//
//    fun withTittle(value: String) :UserBuilder{
//        this.title = value
//        return this
//    }
//
//    fun withAge(value: Int): UserBuilder {
//        this.age = value
//        return this
//    }
//
//    fun withPhone(value: String): UserBuilder {
//        this.phone = value
//        return this
//    }
//
//    fun withEmail(value: String): UserBuilder {
//        this.email = value
//        return this
//    }
//
//    fun withUuid(value: String): UserBuilder {
//        this.uuid = value
//        return this
//    }
//
//    fun withId(value: String): UserBuilder {
//        this.firstName = value
//        return this
//    }
//
//    fun withPicture(value: String): UserBuilder {
//        this.picture = value
//        return this
//    }
//
//    fun build() = User(
//        firstName = firstName,
//        lastName = lastName,
//        title = title,
//        age = age,
//        uuid = uuid,
//        picture = picture,
//        phone = phone,
//        email = email
//    )
}