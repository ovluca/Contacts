package com.qdroid.contacts.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: Int,
    @Json(name = "email") val email: Int,
    @Json(name = "gender") val gender: Int,
    @Json(name = "status") val status: Int,
)
