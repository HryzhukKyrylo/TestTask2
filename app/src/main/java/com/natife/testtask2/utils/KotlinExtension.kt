package com.natife.testtask2.utils

import com.natife.testtask2.data.entities.Human
import com.natife.testtask2.data.entities.UserBuilder

suspend fun <T> getResponse(
    request: suspend () -> T,
    defaultErrorMessage: String
): Resource<T> {
    return try {
        val result = request.invoke()
        return Resource.success(result)
    } catch (e: Throwable) {
        e.printStackTrace()
        Resource.error(data = null, message = defaultErrorMessage)
    }
}

fun Human.toUser() = UserBuilder()
    .withFirstName(name.first)
    .withLastName(name.last)
    .withTittle(name.title)
    .withAge(dob.age)
    .withUuid(login.uuid)
    .withPicture(picture.medium)
    .withPhone(phone)
    .withEmail(email)
    .build()