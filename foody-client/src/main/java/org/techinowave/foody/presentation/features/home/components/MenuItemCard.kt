package org.techinowave.foody.presentation.features.home.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import org.techinowave.domain.model.FoodMenuItem

@Composable
fun MenuItemCard(
    modifier: Modifier = Modifier,
    foodMenuItem: FoodMenuItem,
    context: Context,
    onClick: () -> Unit
) {
    OutlinedCard(
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick() },
        colors = CardDefaults.elevatedCardColors(),
        modifier = modifier
            .height(220.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = foodMenuItem.imgUrl,
            contentDescription = foodMenuItem.itemName,
            imageLoader = ImageLoader(context),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = foodMenuItem.itemName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.height(50.dp)
            )

            Text(
                text = "Price: ${foodMenuItem.price}rs"
            )
        }
    }
}
