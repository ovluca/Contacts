package com.qdroid.contacts.data

import com.qdroid.contacts.data.model.Post
import com.qdroid.contacts.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{userId}/posts")
    suspend fun getUserPosts(@Path("userId") userId: Int): List<Post>
}