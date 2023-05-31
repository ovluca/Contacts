@file:Suppress("UNCHECKED_CAST")

package com.qdroid.contacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qdroid.contacts.data.ApiResult
import com.qdroid.contacts.data.Repository
import com.qdroid.contacts.data.model.User
import com.qdroid.contacts.data.model.initials
import com.qdroid.contacts.data.model.isActive
import com.qdroid.contacts.isOdd
import com.qdroid.contacts.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Success())
    internal val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUsers().collect {
                when (it) {
                    is ApiResult.Error -> _uiState.value = UiState.Failure(it.exception)
                    is ApiResult.Loading -> _uiState.value =
                        UiState.Loading

                    is ApiResult.Success -> _uiState.value =
                        UiState.Success(processData(it.data as List<User>))
                }
            }
        }
    }

    private suspend fun processData(data: List<User>): List<Contact> =
        data.filter { user: User -> user.isActive() }.map { user ->
            Contact(
                id = user.id ?: 0,
                name = user.name ?: "",
                imageUrl = if (user.id?.isOdd() == true) repository.getRedirectedUrl() else "",
                nameInitials = user.initials(),
                email = user.email ?: ""
            )
        }
}