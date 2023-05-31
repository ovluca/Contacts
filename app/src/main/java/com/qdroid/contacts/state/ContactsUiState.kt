package com.qdroid.contacts.state


sealed class ContactsUiState {
    object Loading : ContactsUiState()
    data class Failure(val error: String) : ContactsUiState()
    data class Success(val data: List<Any> = listOf()) :
        ContactsUiState()
}