package com.qdroid.contacts.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qdroid.contacts.R
import com.qdroid.contacts.viewmodel.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsList(contacts: List<Contact>, onClick: (contact: Contact) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_gray))
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
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
                    .padding(
                        vertical = 11.dp,
                        horizontal = dimensionResource(id = R.dimen.box_horizontal_padding)
                    )
            ) {
                Text(text = stringResource(id = R.string.my_contacts), color = Color.Gray)
            }
        }
        items(contacts) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                onClick = { onClick(it) },
                content = {
                    Row(
                        modifier = Modifier
                            .padding(all = dimensionResource(id = R.dimen.box_horizontal_padding)),
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

