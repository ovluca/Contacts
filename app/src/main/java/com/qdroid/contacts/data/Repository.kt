package com.qdroid.contacts.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class Repository(private val apiService: ApiService) {

    suspend fun getUsers() = flow {
        emit(ApiResult.Loading(true))
        try {
            val response = apiService.getUsers()
            emit(ApiResult.Success(response))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getRedirectedUrl(): String = withContext(Dispatchers.IO) {
        val url = "https://picsum.photos/200/200"
        (URL(url).openConnection() as HttpURLConnection).run {
            instanceFollowRedirects = false
            connect()
            when (responseCode) {
                HttpURLConnection.HTTP_MOVED_PERM, HttpURLConnection.HTTP_MOVED_TEMP -> {
                    getHeaderField("Location")
                }

                else -> url
            }
        }
    }

    suspend fun getUserPosts(userId: Int) = flow {
        emit(ApiResult.Loading(true))
        try {
            val response = apiService.getUserPosts(userId)
            emit(ApiResult.Success(response))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}