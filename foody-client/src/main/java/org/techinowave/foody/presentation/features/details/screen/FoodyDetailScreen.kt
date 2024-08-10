package org.techinowave.foody.presentation.features.details.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import org.techinowave.domain.model.FoodMenuItem
import org.techinowave.foody.presentation.features.details.components.AddRemoveItem

@Composable
fun FoodyDetailScreen(
    modifier: Modifier = Modifier,
    context: Context,
    menuItem: FoodMenuItem,
//    navigateToCart: () -> Unit,
    navigateToLogin: () -> Unit
) {

    var itemCount by remember { mutableIntStateOf(1) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(bottom = 12.dp)
    ) {
        AsyncImage(
            model = menuItem.imgUrl,
            contentDescription = menuItem.itemName,
            imageLoader = ImageLoader(context),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = menuItem.itemName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Rs ${menuItem.price}",
            style = MaterialTheme.typography.titleLarge
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            AddRemoveItem(
                itemCount = {
                    itemCount = it
                }
            )

            Button(
                onClick = { navigateToLogin() },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Add Item ₹${menuItem.price * itemCount}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}