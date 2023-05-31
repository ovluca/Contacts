package com.qdroid.contacts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.qdroid.contacts.R
import com.qdroid.contacts.data.model.Post
import com.qdroid.contacts.state.UiState
import com.qdroid.contacts.ui.views.Avatar
import com.qdroid.contacts.viewmodel.Contact
import com.qdroid.contacts.viewmodel.ContactDetailsViewModel

@Composable
fun ContactDetailsScreen(
    navController: NavHostController,
    contact: Contact,
    viewModel: ContactDetailsViewModel
) {
    val contactDetailsUiState by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.loadUserPosts(contact.id)
    }

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
            color = colorResource(id = R.color.dark_gray),
            modifier = Modifier.padding(top = 10.dp)
        )

        when (val uiState = contactDetailsUiState) {
            is UiState.Failure -> {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.info)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.error_image),
                        contentDescription = ""
                    )
                    Text(text = uiState.error, color = Color.Black)
                }
            }

            is UiState.Success -> {
                LazyColumn(modifier = Modifier.padding(top = 34.dp)) {
                    val posts = uiState.data as List<Post>
                    items(posts) {
                        Column(
                            modifier = Modifier
                                .background(colorResource(id = R.color.info))
                        ) {
                            Text(
                                text = it.title ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(
                                    top = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    ),
                                    start = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    ),
                                    end = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    )
                                )
                            )
                            Text(
                                text = it.body ?: "",
                                fontSize = 17.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(
                                    top = 10.dp,
                                    bottom = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    ),
                                    start = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    ),
                                    end = dimensionResource(
                                        id = R.dimen.box_horizontal_padding
                                    )
                                )
                            )
                        }
                    }
                }
            }

            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}