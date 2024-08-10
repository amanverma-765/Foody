package org.techinowave.foody.presentation.features.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodySearchBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    modifier = Modifier.alpha(.4f)
                )
            },
            placeholder = {
                Text(
                    text = "Search for food...",
                    modifier = Modifier.alpha(.5f)
                )
            },
            windowInsets = SearchBarDefaults.windowInsets.exclude(WindowInsets.statusBars),
            modifier = modifier.weight(1f)
        ) {

        }

        FilledIconButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.FilterList,
                contentDescription = "Filter"
            )
        }
    }
}