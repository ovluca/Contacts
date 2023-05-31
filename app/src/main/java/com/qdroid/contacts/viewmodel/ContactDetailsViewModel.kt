@file:Suppress("UNCHECKED_CAST")

package com.qdroid.contacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qdroid.contacts.data.ApiResult
import com.qdroid.contacts.data.Repository
import com.qdroid.contacts.data.model.Post
import com.qdroid.contacts.state.ContactsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ContactsUiState> =
        MutableStateFlow(ContactsUiState.Success())
    internal val uiState: StateFlow<ContactsUiState> = _uiState.asStateFlow()

    fun loadUserPosts(userId: Int) {
        viewModelScope.launch {
            repository.getUserPosts(userId = userId).collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> _uiState.value =
                        ContactsUiState.Failure(apiResult.exception)

                    is ApiResult.Loading -> _uiState.value =
                        ContactsUiState.Loading

                    is ApiResult.Success -> _uiState.value =
                        if ((apiResult.data as List<Post>).isEmpty()) {
                            ContactsUiState.Failure("No Posts")
                        } else {
                            ContactsUiState.Success(apiResult.data)
                        }
                }
            }
        }
    }
}