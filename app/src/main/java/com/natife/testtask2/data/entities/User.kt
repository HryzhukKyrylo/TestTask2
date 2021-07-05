package com.natife.testtask2.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.natife.testtask2.utils.Const

@Entity(tableName = Const.HUMANS)
data class User(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "uuid") val uuid: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "picture") val picture: String?
)

class UserBuilder(
    private var firstName: String = "",
    private var lastName: String = "",
    private var title: String = "",
    private var age: Int = 0,
    private var phone: String = "",
    private var email: String = "",
    private var uuid: String = "",
    private var picture: String = ""
) {

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
}
