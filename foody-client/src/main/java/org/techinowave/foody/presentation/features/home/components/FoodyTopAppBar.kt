package org.techinowave.foody.presentation.features.home.components

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.ImageLoader
import coil.compose.AsyncImage
import org.techinowave.foody.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodyTopAppBar(
    modifier: Modifier = Modifier,
    imgUrl: String?,
    navigateToProfile: () -> Unit,
    navigateToCart: () -> Unit
) {

    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = { navigateToProfile() },
            ) {
                AsyncImage(
                    model = imgUrl?.removeSurrounding("\""),
                    contentDescription = "Profile",
                    imageLoader = ImageLoader(context),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.user_placeholder),
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .fillMaxSize()
                )
            }
        },
        title = {
            Column {
                Text(
                    text = "Deliver to",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Varanasi, India",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                    )
                )
            }
        },
        actions = {
            OutlinedIconButton(
                onClick = { /*TODO*/ },
                border = IconButtonDefaults.outlinedIconButtonBorder(enabled = false)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications"
                )
            }
            OutlinedIconButton(
                onClick = { navigateToCart() },
                border = IconButtonDefaults.outlinedIconButtonBorder(enabled = false)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBag,
                    contentDescription = "Cart"
                )
            }
        }
    )
}