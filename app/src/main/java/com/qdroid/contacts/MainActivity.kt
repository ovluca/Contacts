package com.qdroid.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qdroid.contacts.ui.screens.ContactDetailsScreen
import com.qdroid.contacts.ui.screens.ContactsScreen
import com.qdroid.contacts.ui.theme.ContactsTheme
import com.qdroid.contacts.ui.theme.Screen
import com.qdroid.contacts.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val contactsViewModel: ContactsViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Contacts.route) {
        composable(Screen.Contacts.route) {
            ContactsScreen(navController = navController, viewModel = contactsViewModel)
        }
        composable(Screen.ContactDetails.route) {
            ContactDetailsScreen(navController = navController)
        }
    }
}