package com.qdroid.contacts.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.qdroid.contacts.viewmodel.Contact


@Composable
fun ContactsList(contacts: List<Contact>) {
    LazyColumn {
        items(contacts) {
            Row {
                Text(text = it.nameInitials)
                Text(text = it.name)
            }
        }
    }
}