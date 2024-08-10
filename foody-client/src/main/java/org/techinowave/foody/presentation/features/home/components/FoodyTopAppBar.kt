package org.techinowave.foody.presentation.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodyTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
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
                    contentDescription = "Cart"
                )
            }
            OutlinedIconButton(
                onClick = { /*TODO*/ },
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