package com.qdroid.contacts.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "id") val id: Int?,
    @Json(name = "user_id") val userId: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "body") val body: String?,
)
