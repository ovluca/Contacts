package com.qdroid.contacts.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.qdroid.contacts.R
import com.qdroid.contacts.viewmodel.Contact

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Avatar(modifier: Modifier = Modifier, contact: Contact) {
    if (contact.imageUrl.isNullOrEmpty()) {
        InitialsView(initials = contact.nameInitials)
    } else {
        GlideImage(
            model = contact.imageUrl,
            contentDescription = stringResource(id = R.string.avatar),
            modifier = modifier
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