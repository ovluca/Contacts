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

fun User.isActive() = status == "active"

fun User.nameInitials(): String {
    val ignoreWords = listOf(
        "miss", "mister", "mr", "mrs", "ms",
        "dr", "prof", "sir", "lady", "lord",
        "master", "madam", "capt",
        "col", "maj", "gen", "adm",
        "rev", "hon"
    )
    return name?.split(" ")
        ?.filterNot { it.endsWith(".") || it.lowercase() in ignoreWords }
        ?.mapNotNull { it.firstOrNull()?.uppercase() }
        ?.joinToString("") ?: ""

}