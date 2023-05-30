package com.qdroid.contacts.state

import com.qdroid.contacts.viewmodel.Contact


sealed class ContactsUiState {
    data class Loading(val isLoading: Boolean = true) : ContactsUiState()
    data class Failure(val error: String) : ContactsUiState()
    data class Success(val listOfProducts: List<Contact> = listOf()) :
        ContactsUiState()
}