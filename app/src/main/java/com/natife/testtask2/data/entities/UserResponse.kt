package com.natife.testtask2.data.entities


data class UserResponse(
    val results: List<Human>
)
 data class Human(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)
data class Dob(
    val age: Int,
    val date: String
)
data class Id(
    val name: String,
    val value: String
)

data class Login(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
)data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
data class Registered(
    val age: Int,
    val date: String
)
