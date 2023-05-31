package com.qdroid.contacts.state


sealed class ContactsUiState {
    data class Loading(val isLoading: Boolean = true) : ContactsUiState()
    data class Failure(val error: String) : ContactsUiState()
    data class Success(val data: List<Any> = listOf()) :
        ContactsUiState()
}