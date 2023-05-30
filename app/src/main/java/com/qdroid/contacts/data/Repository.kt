package com.qdroid.contacts.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

}