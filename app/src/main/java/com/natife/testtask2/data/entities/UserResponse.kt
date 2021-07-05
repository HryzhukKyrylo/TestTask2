package com.natife.testtask2.data.entities

data class UserResponse(
    var results: List<Human>,
    var resultsUser: List<User>
)

data class Human(
    val dob: Dob,
    val email: String,
    val login: Login,
    val name: Name,
    val phone: String,
    val picture: Picture,
)

data class Dob(
    val age: Int,
)

data class Id(
    val name: String,
    val value: String
)

data class Login(
    val uuid: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
