package com.qdroid.contacts.state


sealed class UiState {
    object Loading : UiState()
    data class Failure(val error: String) : UiState()
    data class Success(val data: List<Any> = listOf()) :
        UiState()
}