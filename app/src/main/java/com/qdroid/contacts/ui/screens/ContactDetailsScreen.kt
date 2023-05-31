package com.qdroid.contacts.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.qdroid.contacts.viewmodel.toContact

@Composable
fun ContactDetailsScreen(navController: NavHostController, contact: String) {
    Text(text = "Contact Details ${contact.toContact()}")
}