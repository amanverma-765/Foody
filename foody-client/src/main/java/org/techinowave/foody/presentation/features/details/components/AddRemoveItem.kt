package org.techinowave.foody.presentation.features.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AddRemoveItem(
    modifier: Modifier = Modifier,
    itemCount: (Int) -> Unit
) {

    var count by remember { mutableIntStateOf(1) }

    LaunchedEffect(key1 = count) {
        itemCount(count)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = modifier
            .defaultMinSize(minHeight = 50.dp, minWidth = 120.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Icon(
            imageVector = Icons.Rounded.Remove,
            contentDescription = "Remove",
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    if (count > 1) count--
                }
        )

        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add",
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    count++
                }
        )
    }
}