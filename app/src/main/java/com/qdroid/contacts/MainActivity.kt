package com.qdroid.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.qdroid.contacts.ui.screens.ContactDetailsScreen
import com.qdroid.contacts.ui.screens.ContactsScreen
import com.qdroid.contacts.ui.screens.Screen
import com.qdroid.contacts.viewmodel.ContactDetailsViewModel
import com.qdroid.contacts.viewmodel.ContactsViewModel
import com.qdroid.contacts.viewmodel.toContact
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Change the status bar color.
        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(color = Color.White)
            }
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

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val contactsViewModel: ContactsViewModel = viewModel()
    val contactDetailsViewModel: ContactDetailsViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Contacts.route) {
        composable(Screen.Contacts.route) {
            ContactsScreen(navController = navController, viewModel = contactsViewModel)
        }
        composable(Screen.ContactDetails.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("contact")?.let { contactString ->
                contactString.toContact()
                    ?.let { contact ->
                        ContactDetailsScreen(
                            navController = navController,
                            contact = contact,
                            contactDetailsViewModel
                        )
                    }
            }
        }
    }
}