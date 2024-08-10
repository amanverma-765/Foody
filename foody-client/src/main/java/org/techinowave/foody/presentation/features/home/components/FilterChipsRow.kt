package org.techinowave.foody.presentation.features.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterChipsRow(modifier: Modifier = Modifier) {

    val selectedIndex = remember { mutableIntStateOf(0) }

    FlowColumn(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachColumn = 1,
        modifier = modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
    ) {

        (1..10).forEachIndexed { index, _->
            ElevatedFilterChip(
                selected = index == selectedIndex.intValue,
                onClick = { /*TODO*/ },
                label = { Text(text = "All") },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

private enum class FilterChips {
    ALL,
    FAST_FOOD,
    ROLLS,
}