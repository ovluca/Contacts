package com.qdroid.contacts.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.qdroid.contacts.R
import com.qdroid.contacts.viewmodel.Contact

@Composable
fun ContactsList(contacts: List<Contact>) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = dimensionResource(id = R.dimen.box_horizontal_padding)
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.contacts),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(
                        vertical = 11.dp,
                        horizontal = dimensionResource(id = R.dimen.box_horizontal_padding)
                    )
            ) {
                Text(text = stringResource(id = R.string.my_contacts), color = Color.Gray)
            }
        }
        items(contacts) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RectangleShape,
                content = {
                    Row(
                        modifier = Modifier.padding(all = dimensionResource(id = R.dimen.box_horizontal_padding)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Avatar(contact = it)
                        Text(
                            text = it.name,
                            modifier = Modifier
                                .padding(
                                    horizontal = dimensionResource(id = R.dimen.text_horizontal_padding),
                                    vertical = 0.dp
                                )
                                .weight(1f),
                            color = Color.Black,
                            fontSize = 17.sp,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            contentDescription = stringResource(id = R.string.arrow)
                        )

                    }
                })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Avatar(contact: Contact) {
    if (contact.imageUrl.isNullOrEmpty()) {
        InitialsView(initials = contact.nameInitials)
    } else {
        GlideImage(
            model = contact.imageUrl,
            contentDescription = stringResource(id = R.string.avatar),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.avatar_size))
                .clip(CircleShape),
        )
    }
}

@Composable
fun InitialsView(initials: String) {
    Box(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.avatar_size))
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}