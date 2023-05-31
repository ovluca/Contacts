package com.qdroid.contacts.ui.screens

sealed class Screen(val route: String) {
    object Contacts : Screen("contacts")
    object ContactDetails : Screen("contactDetails/{contact}")
}
