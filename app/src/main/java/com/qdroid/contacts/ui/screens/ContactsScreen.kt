package com.qdroid.contacts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.qdroid.contacts.R
import com.qdroid.contacts.state.ContactsUiState
import com.qdroid.contacts.ui.theme.Screen
import com.qdroid.contacts.ui.views.ContactsList
import com.qdroid.contacts.viewmodel.ContactsViewModel

@Composable
fun ContactsScreen(navController: NavHostController, viewModel: ContactsViewModel) {
    val contactsUiState by viewModel.uiState.collectAsState()
    when (contactsUiState) {
        is ContactsUiState.Failure -> Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.error_image), contentDescription = "")
            Text(text = (contactsUiState as ContactsUiState.Failure).error)
        }

        is ContactsUiState.Loading -> Box(
            modifier = androidx.compose.ui.Modifier
                .padding(20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is ContactsUiState.Success -> ContactsList(contacts = (contactsUiState as ContactsUiState.Success).listOfProducts)
    }

}