package com.qdroid.contacts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.qdroid.contacts.R
import com.qdroid.contacts.ui.views.Avatar
import com.qdroid.contacts.viewmodel.Contact

@Composable
fun ContactDetailsScreen(navController: NavHostController, contact: Contact) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { navController.navigateUp() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = stringResource(
                    id = R.string.arrow
                )
            )
            Text(
                text = stringResource(id = R.string.contact_details),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Avatar(modifier = Modifier.padding(top = 32.dp), contact = contact)
        Text(
            text = contact.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = contact.email,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}