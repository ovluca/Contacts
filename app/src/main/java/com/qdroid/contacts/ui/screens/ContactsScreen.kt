package com.qdroid.contacts.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.qdroid.contacts.ui.theme.Screen

@Composable
fun ContactsScreen(navController: NavHostController) {
    Column {
        Text(text = "Contacts")
        Button(onClick = { navController.navigate(Screen.ContactDetails.route) }) {
            Text(text = "Go to Contact Details")
        }
    }
}