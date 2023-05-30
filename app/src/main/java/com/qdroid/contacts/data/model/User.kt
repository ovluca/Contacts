package com.qdroid.contacts.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "status") val status: String?,
)

fun User.nameInitials(): String =
    name?.split(" ")
        ?.filterNot { it.endsWith(".") }
        ?.mapNotNull { it.firstOrNull()?.uppercase() }
        ?.joinToString("") ?: ""